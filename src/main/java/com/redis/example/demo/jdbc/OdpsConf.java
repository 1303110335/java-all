package com.redis.example.demo.jdbc;

/**
 * @author: kui.zhouk
 * @date: 2019-09-27
 */
public class OdpsConf {

    private String url;
    private String tunnelUrl;
    private String project;
    private String table;
    private String tableName;
    private String accessId;
    private String accessKey;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTunnelUrl() {
        return tunnelUrl;
    }

    public void setTunnelUrl(String tunnelUrl) {
        this.tunnelUrl = tunnelUrl;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    public String toString() {
        return "OdpsConf{" +
                "url='" + url + '\'' +
                ", tunnelUrl='" + tunnelUrl + '\'' +
                ", project='" + project + '\'' +
                ", table='" + table + '\'' +
                ", accessId='" + accessId + '\'' +
                ", accessKey='" + accessKey + '\'' +
                '}';
    }
}
