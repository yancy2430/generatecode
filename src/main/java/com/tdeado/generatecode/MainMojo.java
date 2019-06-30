package com.tdeado.generatecode;

import com.tdeado.generatecode.database.DatabaseOperate;
import com.tdeado.generatecode.utils.FileUtils;
import com.tdeado.generatecode.utils.UploadEntity;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.util.ArrayList;

/**
 * 生成DAO代码
 * @goal update-baseentity
 */
public class MainMojo extends AbstractMojo {
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

    /**
     * @parameter expression="${prefix}"
     */
    private String prefix;
    /**
     * @parameter expression="${host}"
     */
    private String host;
    /**
     * @parameter expression="${port}"
     */
    private int port;
    /**
     * @parameter expression="${user}"
     */
    private String user;
    /**
     * @parameter expression="${dbname}"
     */
    private String dbname;
    /**
     * @parameter expression="${pass}"
     */
    private String pass;
    /**
     * @parameter expression="${entityurl}"
     */
    private String entityurl;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        DatabaseOperate operate = new DatabaseOperate(host,port,user,pass,dbname);
        Generating generating = new Generating(artifactId,groupId,basedir,prefix);
        for (String tableName : operate.getTableNames()) {
            try {
                generating.createBaseServiceFromModel(tableName,operate,false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        UploadEntity.clone(entityurl+"/clone");

        ArrayList<Object> list = FileUtils.scanFilesWithNoRecursion(basedir+"/src/main/java/"+groupId.replace(".","/")+"/"+artifactId+"/entity/");
        for (Object o : list) {
            System.err.println(o);
            UploadEntity.upload(new File(o.toString()),artifactId,entityurl+"/receive");
            FileUtils.delFile(o.toString());
        }
        FileUtils.delFile(basedir+"/src/main/java/"+groupId.replace(".","/")+"/"+artifactId+"/entity");

        UploadEntity.deploy(entityurl+"/deploy?v="+UploadEntity.push(entityurl+"/push"));
    }

    public static void main(String[] args) {
        ArrayList<Object> list = FileUtils.scanFilesWithNoRecursion("/Users/yangzhe/generatecode/src/main/java/com/tdeado/generatecode");
        for (Object o : list) {
            System.err.println(o);
        }

    }

}
