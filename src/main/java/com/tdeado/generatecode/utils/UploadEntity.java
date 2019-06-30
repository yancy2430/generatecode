package com.tdeado.generatecode.utils;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UploadEntity {
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .callTimeout(3600, TimeUnit.SECONDS)
            .connectTimeout(3600, TimeUnit.SECONDS)
            .readTimeout(3600, TimeUnit.SECONDS)
            .writeTimeout(3600, TimeUnit.SECONDS)
            .build();
    public static String clone(String cloneUrl) {
        try {
            return okHttpClient.newCall(new Request.Builder()
                    .get()
                    .url(cloneUrl)
                    .build()).execute().body().string();

        } catch (IOException e) {
        }
        return "error";
    }
    public static void upload(File file,String module,String uploadUrl) {
        try {
            // MultipartBuilder，是上传文件的query
            // addFormDataPart方法：@param [String]name, [String]value
            // addFormDataPart方法：@param [String]name, [String]fileName, [String]fileType, [String]file
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("file",file.getName(),RequestBody.create(null, file))
                    .addFormDataPart("module",module)
                    .build();

            // request方法： @param [String]URL, [RequestBody]requestBody
            Request request = new Request.Builder()
                    .url(uploadUrl)
                    .post(requestBody)
                    .build();

            // response储存服务器的回应
            Response response = okHttpClient.newCall(request).execute();
            // 把response转换成string
            System.err.println("upload "+file.getPath()+":"+response.body().string());

        } catch (IOException e) {
        }
    }
    public static String push(String pushUrl) {
        try {
            return okHttpClient.newCall(new Request.Builder()
                    .get()
                    .url(pushUrl)
                    .build()).execute().body().string();

        } catch (IOException e) {
        }
        return "error";
    }
    public static void deploy(String deployUrl) {
        try {
            synchronized(deployUrl){
                System.err.println("\n\n********引入新依赖********\n"+okHttpClient.newCall(new Request.Builder()
                        .get()
                        .url(deployUrl)
                        .build()).execute().body().string()+"\n****************\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
