package com.tdeado.generatecode.database;


import java.sql.*;
import java.util.*;

import static com.tdeado.generatecode.utils.StrUtils.underLineToCamel;


public class DatabaseOperate {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String SQL = "SELECT * FROM ";// 数据库操作
    private String url;
    private String user;
    private String pass;
    private String dbname;

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
        }
    }

    public DatabaseOperate(String host, int port, String user, String pass, String dbname) {
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true&useSSL=false";
        this.user = user;
        this.pass = pass;
        this.dbname = dbname;
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    private void closeConnection(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tableNames;
    }

    public TableData getTableData(String tableName) {
        TableData tableData = new TableData();
        tableData.setTableName(tableName);
        tableData.setColumns(getTableColumns(tableName));
        Optional<MySqlData> optionalMySqlData = tableData.getColumns().stream().filter(m -> !m.getIsAuto().equals("NO")).findFirst();
        optionalMySqlData.ifPresent(mySqlData -> tableData.setAutoKey(mySqlData.getPropertiesName()));
        return tableData;
    }

    private List<MySqlData> getTableColumns(String tableName) {
        List<MySqlData> mySqlDataList = new ArrayList<MySqlData>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            Set<String> keySet = new HashSet<>();
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            rs = databaseMetaData.getPrimaryKeys(null, dbname, tableName);
            while (rs.next()) {
                keySet.add(rs.getString("COLUMN_NAME"));
            }
            rs = databaseMetaData.getColumns(null, dbname, tableName, "%");

            while (rs.next()) {
                MySqlData mySqlData = new MySqlData();
                mySqlData.setColumnName(rs.getString("COLUMN_NAME"));
                mySqlData.setPropertiesName(underLineToCamel(mySqlData.getColumnName(), true));
                mySqlData.setTypeId(rs.getInt("DATA_TYPE"));
                mySqlData.setTypeName(rs.getString("TYPE_NAME"));
                mySqlData.setJavaTypeName(transfer(mySqlData.getTypeName()));
                mySqlData.setIsBetween(isBetween(mySqlData.getTypeName()));
                mySqlData.setRemarks(rs.getString("REMARKS"));
                mySqlData.setNullAble(rs.getInt("NULLABLE"));
                mySqlData.setColumnDef(rs.getString("COLUMN_DEF"));
                mySqlData.setIsAuto(rs.getString("IS_AUTOINCREMENT"));
                if (keySet.contains(mySqlData.getColumnName())) {
                    mySqlData.setIsKey(1);
                } else {
                    mySqlData.setIsKey(0);
                }
                mySqlDataList.add(mySqlData);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                }
            }
        }

        return mySqlDataList;
    }


    public static class DBTb {
        private String tbName;
        private MySqlData tbInfo;

        public DBTb(String tbName, MySqlData tbInfo) {
            this.tbName = tbName;
        }

        public String getTbName() {
            return tbName;
        }

        public void setTbName(String tbName) {
            this.tbName = tbName;
        }

        public MySqlData getTbInfo() {
            return tbInfo;
        }

        public void setTbInfo(MySqlData tbInfo) {
            this.tbInfo = tbInfo;
        }
    }


    private String transfer(String typeName) {
        typeName = typeName.replaceAll(" UNSIGNED", "");
        switch (typeName) {
            case "VARCHAR":
                return "String";
            case "CHAR":
                return "String";
            case "TEXT":
                return "String";
            case "INTEGER":
                return "Long";
            case "BIGINT":
                return "Long";
            case "TINYINT":
                return "Integer";
            case "SMALLINT":
                return "Integer";
            case "MEDIUMINT":
                return "Integer";
            case "INT":
                return "Integer";
            case "FLOAT":
                return "Float";
            case "DOUBLE":
                return "Double";
            case "DECIMAL":
                return "java.math.BigDecimal";
            case "DATE":
                return "java.time.LocalDate";
            case "TIME":
                return "java.time.LocalTime";
            case "TIMESTAMP":
                return "java.time.LocalDateTime";
            case "MEDIUMTEXT":
                return "String";
            case "LONGTEXT":
                return "String";
            case "DATETIME":
                return "java.time.LocalDateTime";
            case "BIT":
                return "Integer";
            case "TINYTEXT":
                return "String";
            default:
                throw new IllegalArgumentException(typeName + " no such typeName,please edit db.mysql.process.TypeSwitch");
        }
    }

    private String isBetween(String typeName) {
        typeName = typeName.replaceAll(" UNSIGNED", "").toUpperCase();
        switch (typeName) {
            case "VARCHAR":
                return "no";
            case "CHAR":
                return "no";
            case "TEXT":
                return "no";
            case "INTEGER":
                return "yes";
            case "BIGINT":
                return "yes";
            case "TINYINT":
                return "yes";
            case "SMALLINT":
                return "yes";
            case "MEDIUMINT":
                return "yes";
            case "INT":
                return "yes";
            case "FLOAT":
                return "yes";
            case "DOUBLE":
                return "yes";
            case "DECIMAL":
                return "yes";
            case "DATE":
                return "yes";
            case "TIME":
                return "yes";
            case "TIMESTAMP":
                return "yes";
            case "MEDIUMTEXT":
                return "no";
            case "LONGTEXT":
                return "no";
            case "DATETIME":
                return "yes";
            case "BIT":
                return "yes";
            case "INT8":
                return "yes";
            case "INT4":
                return "yes";
            case "INT2":
                return "yes";
            case "BOOL":
                return "no";
            case "FLOAT4":
                return "yes";
            case "FLOAT8":
                return "yes";
            case "BIGSERIAL":
                return "yes";
            case "SERIAL":
                return "yes";
            case "TINYTEXT":
                return "no";
            default:
                throw new IllegalArgumentException(typeName + " no such typeName,please edit db.mysql.process.TypeSwitch");
        }
    }

    private boolean isNeedChange(String content) {
        return content.contains("_");
    }
}