package com.ksc.redis.model.instance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/***
 * @ClassName: DescribeCacheByRoleResponse
 * @Description:TODO
 * @version : V1.0
 */
public class DescribeCacheByRoleResponse {
    private String instanceId;
    private String name;
    private String port;
    private String ip;
    private String status;
    private Date createTime;
    private String proxy;
    private String area;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


}
