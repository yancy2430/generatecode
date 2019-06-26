package com.tdeado.generatecode.utils;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StrUtils {

    /**
     * 参数转json
     *
     * @param string
     * @return
     */
    public static String paramTojson(String string) {
        Map<String, String> stringMap = new HashMap<String, String>();
        String[] strs = string.split("&");
        for (String str : strs) {
            String[] sts = new String[]{"", ""};
            String[] st = str.split("=");
            if (st.length == 1) {
                sts[0] = st[0];
            } else {
                sts[0] = st[0];
                sts[1] = st[1];
            }
            stringMap.put(sts[0], sts[1]);
        }
        return new Gson().toJson(stringMap);
    }

    /**
     * bean转json
     *
     * @param string
     * @return
     */
    public static String beanTojson(Object string) {
        return new Gson().toJson(string);
    }

    /**
     * bean转参数
     *
     * @param object
     * @return
     */
    public static String beanToParam(Object object) {
        return jsonToParam(new Gson().toJson(object));
    }

    /**
     * json转参数
     *
     * @param string
     * @return
     */
    public static String jsonToParam(String string) {
        Map<String, String> map = new Gson().fromJson(string, Map.class);
        String str = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str += entry.getKey() + "=" + entry.getValue() + "&";
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * json转bean
     *
     * @param string
     * @return
     */
    public static Object jsonTobean(String string, Class<?> t) {
        return new Gson().fromJson(string, t);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(long seconds, String format) {
        if (seconds == 0) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(seconds));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }


    /**
     * 下划线转驼峰
     *
     * @param content
     * @param firstUpperCase
     * @return
     */
    public static String underLineToCamel(String content, boolean firstUpperCase) {
        if (content == null || content.length() == 0) {
            return "";
        }

        if (!isNeedChange(content)) {
            return content;
        }

        String result = Stream.of(content.split("_")).map(m -> {
            String text = m;
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
            return text;
        }).collect(Collectors.joining());
        if (firstUpperCase) {
            return result.substring(0, 1).toLowerCase() + result.substring(1);
        } else {
            return result;
        }
    }
    /**
     * 驼峰转下划线
     * @return
     */
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    /**
     * 首字母大写
     *
     * @param content
     * @return 首字母大写字符串
     */
    public static String capitalized(String content) {
        if (content == null || content.length() == 0) {
            return "";
        }
        return content.substring(0, 1).toUpperCase()+ content.substring(1);
    }

    public static void main(String[] args) {
        System.err.println(capitalized(underLineToCamel("work_order",true)));
    }

    private static boolean isNeedChange(String content) {
        return content.contains("_");
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str 字符串
     * @return 是否是数字
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
