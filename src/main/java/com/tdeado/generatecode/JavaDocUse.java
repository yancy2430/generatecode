package com.tdeado.generatecode;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

/**
 * JavaDoc的使用方法
 */
public class JavaDocUse {
    private static RootDoc rootDoc;

    public static class Doclet {
        public static boolean start(RootDoc rootDoc) {
            JavaDocUse.rootDoc = rootDoc;
            return true;
        }
    }

    /**
     * 显示DocRoot中的基本信息
     */
    public static String show() {
        ClassDoc[] classes = rootDoc.classes();
        for (ClassDoc classDoc : classes) {
            return classDoc.commentText();
        }
        return "";
    }

    public static String getCommentText(String classPath) {
        if (!classPath.contains(".java")) {
            return "";
        }
        com.sun.tools.javadoc.Main.execute(new String[]{"-doclet",
                Doclet.class.getName(),
                "-encoding", "utf-8", "-classpath",
                "/Users/yangzhe/新框架/common/target",
                classPath});
        return show();
    }

    public static void main(String[] args) {
        getCommentText("/Users/yangzhe/新框架/common/src/main/java/com/bingo/upup/common/remote/IdgenerateService.java");
    }
}