package com.tdeado.generatecode;

import java.util.List;

public class Parsing {
    String title;
    boolean auto;//auto
    boolean show;//hide
    String dataType;//time date text num
    String findType;//like find between
    List list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFindType() {
        return findType;
    }

    public void setFindType(String findType) {
        this.findType = findType;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}