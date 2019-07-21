package com.tdeado.generatecode.utils;

import com.tdeado.generatecode.Parsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsingUtils {
    public Parsing parsing(String str) {
        String[] split = str.split("\\|");
        Parsing bean = new Parsing();
        bean.setShow(true);
        for (String s : split) {

            if (s.contains(":")) {
                String[] arr = s.split(":");
                if (arr[1].contains(",") && arr[1].contains(".")) {
                    bean.setDataType(arr[0]);
                    List<Map> list = new ArrayList<>();
                    for (String s1 : arr[1].split(",")) {
                        String[] kv = s1.split("\\.");
                        Map<String, String> stringMap = new HashMap<>();
                        stringMap.put("t", kv[0]);
                        stringMap.put("v", kv[1]);
                        list.add(stringMap);
                    }
                    bean.setList(list);
                } else {
                    switch (arr[0]) {
                        case "data":
                            bean.setDataType(arr[1]);
                            break;
                        case "find":
                            bean.setFindType(arr[1]);
                            break;
                    }

                }
            } else {
                if (s.equals("auto")) {
                    bean.setAuto(true);
                } else if (s.equals("hide")) {
                    bean.setShow(false);
                } else {
                    bean.setTitle(s);
                }
            }

        }
        return bean;

    }
}
