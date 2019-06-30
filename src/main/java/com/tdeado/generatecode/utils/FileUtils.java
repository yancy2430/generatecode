package com.tdeado.generatecode.utils;


import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileUtils {
    /**
     * TODO:非递归方式扫描指定文件夹下面的所有文件
     *
     * @param folderPath 需要进行文件扫描的文件夹路径
     * @return ArrayList<Object>
     * @author 邪恶小先生（LQ）
     * @time 2017年11月3日
     */
    public static ArrayList<Object> scanFilesWithNoRecursion(String folderPath) {
        ArrayList<Object> scanFiles = new ArrayList<Object>();

        /**linkedList实现**/
        LinkedList<File> queueFiles = new LinkedList<File>();
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
        } else {
            //首先将第一层目录扫描一遍
            File[] files = directory.listFiles();
            //遍历扫出的文件数组，如果是文件夹，将其放入到linkedList中稍后处理
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    queueFiles.add(files[i]);
                } else {
                    //暂时将文件名放入scanFiles中
                    scanFiles.add(files[i].getAbsolutePath());
                }
            }

            //如果linkedList非空遍历linkedList
            while (!queueFiles.isEmpty()) {
                //移出linkedList中的第一个
                File headDirectory = queueFiles.removeFirst();
                File[] currentFiles = headDirectory.listFiles();
                for (int j = 0; j < currentFiles.length; j++) {
                    if (currentFiles[j].isDirectory()) {
                        //如果仍然是文件夹，将其放入linkedList中
                        queueFiles.add(currentFiles[j]);
                    } else {
                        scanFiles.add(currentFiles[j].getAbsolutePath());
                    }
                }
            }
        }

        return scanFiles;
    }

    public static boolean delFile(String path) {
        File file = new File(path);
        if (file.exists())
            return file.delete();
        else
            return false;
    }
}
