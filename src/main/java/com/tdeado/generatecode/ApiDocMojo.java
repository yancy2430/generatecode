package com.tdeado.generatecode;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.util.ArrayList;

/**
 * 使用文档的方式
 * @goal apidoc
 */
public class ApiDocMojo extends AbstractMojo {

    /**
     * @parameter expression="${controllerdir}"
     */
    private String controllerdir;
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
        //创建文档文件夹
        File f = new File(basedir+"/src/main/resources/apidoc/");
        if ( !f.exists() ) {
            f.mkdirs();
        }
        ArrayList<String> listFileName = new ArrayList<String>();

        for (String name : listFileName) {
            if (name.contains(".java")) {
                System.out.println(name);
            }
        }


    }
}
