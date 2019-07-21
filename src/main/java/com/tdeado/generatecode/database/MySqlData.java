package com.tdeado.generatecode.database;


import java.util.List;

/**
 * db.mysql
 * Created by ASUS on 2017/7/16.
 * 10:29
 */
public class MySqlData {
    /**
     * 是否为主键
     */
    private Integer isKey;
    /**
     * 是否自增
     */
    private String isAuto;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 属性名
     */
    private String propertiesName;
    /**
     * 数据库类型ID
     */
    private Integer typeId;
    /**
     * 数据库类型名
     */
    private String typeName;
    /**
     * java类型名
     */
    private String javaTypeName;
    /**
     * 注释
     */
    private String remarks;

    /**
     * 说明
     */
    private String explain="";

    /**
     * 是否可以为NULL
     */
    private Integer nullAble;

    /**
     * 默认值
     */
    private String columnDef;

    private String isBetween;

    private String showType;


    private List<KV> kvs;


    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }



    public String getIsBetween() {
        return isBetween;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public void setIsBetween(String isBetween) {
        this.isBetween = isBetween;
    }

    public Integer getNullAble() {
        return nullAble;
    }

    public void setNullAble(Integer nullAble) {
        this.nullAble = nullAble;
    }

    public String getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }

    public String getRemarks() {

        return this.remarks;
    }


    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getJavaTypeName() {
        return javaTypeName;
    }

    public void setJavaTypeName(String javaTypeName) {
        this.javaTypeName = javaTypeName;
    }

    public Integer getIsKey() {
        return isKey;
    }

    public void setIsKey(Integer isKey) {
        this.isKey = isKey;
    }

    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto;
    }
    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public List<KV> getKvs() {
        return kvs;
    }

    public void setKvs(List<KV> kvs) {
        this.kvs = kvs;
    }

    @Override
    public String toString() {
        return "MySqlData{" +
                "isKey=" + isKey +
                ", isAuto='" + isAuto + '\'' +
                ", columnName='" + columnName + '\'' +
                ", propertiesName='" + propertiesName + '\'' +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", javaTypeName='" + javaTypeName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", nullAble=" + nullAble +
                ", columnDef='" + columnDef + '\'' +
                ", isBetween='" + isBetween + '\'' +
                ", showType='" + showType + '\'' +
                ", explain='" + explain + '\'' +
                ", kvs=" + kvs +
                '}';
    }
}
