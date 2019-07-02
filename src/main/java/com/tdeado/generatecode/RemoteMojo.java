package com.tdeado.generatecode;

import com.tdeado.generatecode.database.DatabaseOperate;
import com.tdeado.generatecode.utils.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.util.ArrayList;

/**
 * 生成DAO代码
 * @goal remote
 */
public class RemoteMojo extends AbstractMojo {
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


        ArrayList<Object> list = FileUtils.scanFilesWithNoRecursion(basedir+"/src/main/java/"+groupId.replace(".","/")+"/"+artifactId+"/entity/");
        for (Object o : list) {
            System.err.println(o);
        }
    }


}
