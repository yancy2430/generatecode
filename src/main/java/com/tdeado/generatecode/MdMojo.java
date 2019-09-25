package com.tdeado.generatecode;

import com.google.gson.Gson;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成DAO代码
 *
 * @goal init-file-md
 */
public class MdMojo extends AbstractMojo {
    /**
     * @parameter expression="${basedir}"
     */
    private String basedir;
    /**
     * @parameter expression="${project.groupId}"
     */
    private String groupId;
    /**
     * @parameter expression="${project.artifactId}"
     */
    private String artifactId;
    /**
     * @parameter expression="${project.build.directory}"
     */
    private String outobjpath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        treeList(basedir+"/src/main",0);

        Map<String, Object> root = new HashMap<String, Object>();
        Gson gson = new Gson();
        root.put("list", strings);
        // 通过FreeMarker的Confuguration读取相应的模板文件
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        // 设置默认字体
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(Generating.class, "/static");
        //生成bootstrap
        Template template = null;
        try {
            template = configuration.getTemplate("directoryTree.ftl");
            File file = new File(basedir+"/doc/filedoc.md");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            FileWriter out = new FileWriter(file);
            //模板输出静态文件
            template.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    static List<String> strings = new ArrayList<>();

    public static void treeList(String basedir, int lv) {


        File file = new File(basedir);
        for (int s = 0; s < file.listFiles().length; s++) {
            String str = "";
            for (int i = 0; i < lv; i++) {
                if (i == lv - 1) {
                    str += "    ";
                } else {
                    str += "    ";
                }
            }
            String comment = "";
            if (!file.listFiles()[s].isDirectory()){
                comment = JavaDocUse.getCommentText(file.listFiles()[s].getPath());
            }

            if (s == file.listFiles().length - 1) {
                strings.add(str + "└── " + file.listFiles()[s].getName() + "    "+comment);
            } else {
                strings.add(str + "├── " + file.listFiles()[s].getName()+ "    "+comment);
            }
            if (file.listFiles()[s].isDirectory()) {
                treeList(file.listFiles()[s].getPath(), lv + 1);
            }
        }
    }


    @Data
    public static class DirBean {
        private String path;
        private List<DirBean> list;
        private boolean directory;

    }
}
