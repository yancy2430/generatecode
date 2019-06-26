package com.tdeado.generatecode;

import com.tdeado.generatecode.database.DatabaseOperate;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * 生成DAO代码
 * @goal codegeneration
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
     * @parameter expression="${init}"
     */
    private boolean init;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        DatabaseOperate operate = new DatabaseOperate(host,port,user,pass,dbname);
        Generating generating = new Generating(artifactId,groupId,basedir,prefix);
        if (init){
            generating.inits();
        }
        for (String tableName : operate.getTableNames()) {
            try {
                generating.createBaseServiceFromModel(tableName,operate,init);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
