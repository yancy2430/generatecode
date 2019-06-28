package com.tdeado.generatecode;

import com.tdeado.generatecode.database.DatabaseOperate;
import com.tdeado.utils.json.GsonUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.tdeado.generatecode.utils.StrUtils.capitalized;
import static com.tdeado.generatecode.utils.StrUtils.underLineToCamel;


public class Generating {
    private String name;
    private String packageName;
    private String projectPath;
    private File fileTemplate;//模板路径
    private String prefix;//模板路径

    public Generating(String name, String packageName, String projectPath, String prefix) {
        this.name = name;
        this.packageName = packageName;
        this.projectPath = projectPath;
        this.prefix = prefix;
        fileTemplate = new File("classpath:/static");//模板路径

    }


    public void inits() {
        try {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("name", name);
            root.put("AppName", capitalized(name));
            root.put("packageName", packageName + "." + name);
            root.put("basePackageName", packageName);
            root.put("timeStamp", String.valueOf(System.currentTimeMillis()));
            root.put("sense", "`");
            init(root);//chuangjian
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成 代码
     *
     * @param tableName
     */
    public void createBaseServiceFromModel(String tableName, DatabaseOperate operate, boolean is) throws Exception {

        Map<String, Object> object = new HashMap<String, Object>();
        String className = capitalized(underLineToCamel(tableName.replace(prefix, ""), true));
        object.put("name", name);
        object.put("className", className);
        object.put("packageName", packageName + "." + name);
        object.put("basePackageName", packageName);
        object.put("attrs", operate.getTableData(tableName).getColumns());
        object.put("tableAttrs", operate.getTableData(tableName));
        object.put("tableName", tableName);
        object.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        object.put("sense", "`");


        System.err.println(GsonUtil.beanTojson(operate.getTableData(tableName).getColumns()));
        FileWriter out = null;
        // 通过FreeMarker的Confuguration读取相应的模板文件
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        // 设置默认字体
        configuration.setDefaultEncoding("utf-8");
        // 设置模板路径
        configuration.setClassForTemplateLoading(Generating.class, "/static");

        // 获取模板
        Template template = configuration.getTemplate("base/BaseService.ftl");
        //设置输出文件
        createNewFileNameExist("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/service/base/Base" + object.get("className").toString() + "Service.java",out,object,template);

        //BaseServiceImpl
        configuration.setClassForTemplateLoading(Generating.class, "/static");
        template = configuration.getTemplate("base/BaseServiceImpl.ftl");
        createNewFileNameExist("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/service/base/impl/Base" + object.get("className").toString() + "ServiceImpl.java",out,object,template);

        //BaseController
        configuration.setClassForTemplateLoading(Generating.class, "/static");
        template = configuration.getTemplate("base/BaseController.ftl");
        createNewFileNameExist("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/web/base/Base" + object.get("className").toString() + "Controller.java",out,object,template);

        //生成entity
        template = configuration.getTemplate("base/entity.ftl");
        //设置输出文件
        createNewFileNameExist("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/entity/" + object.get("className").toString() + ".java",out,object,template);


        //BaseMapper
        configuration.setClassForTemplateLoading(Generating.class, "/static");
        template = configuration.getTemplate("base/BaseMapper.ftl");
        createNewFileNameExist("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/dao/base/Base" + object.get("className").toString() + "Mapper.java",out,object,template);

        //生成BaseMapper xml
        template = configuration.getTemplate("base/MapperXml.ftl");
        //设置输出文件
        createNewFileNameExist("src/main/resources/mapper/base/Base" + object.get("className").toString() + "Mapper.xml",out,object,template);


        //生成doc
        template = configuration.getTemplate("doc/md.ftl");
        //设置输出文件
        createNewFileName("src/main/resources/doc/" + object.get("className").toString() + ".md",out,object,template);

        //生成Service
        template = configuration.getTemplate("Service.ftl");
        createNewFileName("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/service/" + object.get("className").toString() + "Service.java",out,object,template);

        //ServiceImpl
        template = configuration.getTemplate("ServiceImpl.ftl");
        createNewFileName("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/service/impl/" + object.get("className").toString() + "ServiceImpl.java",out,object,template);
        //Controller
        template = configuration.getTemplate("Controller.ftl");
        createNewFileName("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/web/" + object.get("className").toString() + "Controller.java",out,object,template);

        //Mapper
        template = configuration.getTemplate("Mapper.ftl");
        createNewFileName("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/dao/" + object.get("className").toString() + "Mapper.java",out,object,template);

        //生成xml
        template = configuration.getTemplate("MapperXmlEmpty.ftl");
        //设置输出文件
        createNewFileName("src/main/resources/mapper/" + object.get("className").toString() + "Mapper.xml",out,object,template);

    }


    private void init(Map<String, Object> object) throws IOException, TemplateException {
        FileWriter out = null;
        // 通过FreeMarker的Confuguration读取相应的模板文件
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        // 设置默认字体
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(Generating.class, "/static");
        Template template = null;
        File file = null;
        // 设置模板路径

        //生成bootstrap
        template = configuration.getTemplate("xml/bootstrap.ftl");
        file = createNewFileName("src/main/resources/bootstrap.properties",out,object,template);

        //生成Application.ftl
        template = configuration.getTemplate("Application.ftl");
        file = createNewFileName("src/main/java" + "/" + packageName.replace(".", "/") + "/" + name + "/" + object.get("AppName") + "Application.java",out,object,template);
    }

    public File createNewFileName(String strPath,FileWriter out,Map<String, Object> object,Template template) throws IOException, TemplateException {

        File file = new File(projectPath + "/" + strPath);
        File fileParent = file.getParentFile();
        System.err.println("createNewFileName:"+file.getPath()+":"+file.exists());
        if (file.exists()){
            return file;
        }
        return getFile(file, fileParent,out,object,template);
    }

    public File createNewFileNameExist(String strPath,FileWriter out,Map<String, Object> object,Template template) throws IOException, TemplateException {
        File file = new File(projectPath + "/" + strPath);
        File fileParent = file.getParentFile();
        System.err.println("createNewFileNameExist:"+file.getPath()+":"+file.exists());
        return getFile(file, fileParent,out,object,template);
    }
    private File getFile(File file, File fileParent,FileWriter out,Map<String, Object> object,Template template) throws IOException, TemplateException {
        try {
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            file.createNewFile();
            out = new FileWriter(file);
            //模板输出静态文件
            template.process(object, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public String readToString(String fileName) {
        String encoding = "ISO-8859-1";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }


}
