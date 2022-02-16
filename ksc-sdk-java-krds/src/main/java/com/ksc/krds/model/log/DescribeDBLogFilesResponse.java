package com.ksc.krds.model.log;

import java.util.List;

public class DescribeDBLogFilesResponse {
    List<LogFile> DescribeDBLogFiles;

    private Integer TotalCount;

    private Integer Marker;

    private Integer MaxRecords;

    private String RequestId;
    private String Message;
    private Boolean Return;
    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Boolean getReturn() {
        return Return;
    }

    public void setReturn(Boolean aReturn) {
        Return = aReturn;
    }

    public List<LogFile> getDescribeDBLogFiles() {
        return DescribeDBLogFiles;
    }

    public void setDescribeDBLogFiles(List<LogFile> describeDBLogFiles) {
        DescribeDBLogFiles = describeDBLogFiles;
    }

    public Integer getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(Integer totalCount) {
        TotalCount = totalCount;
    }

    public Integer getMarker() {
        return Marker;
    }

    public void setMarker(Integer marker) {
        Marker = marker;
    }

    public Integer getMaxRecords() {
        return MaxRecords;
    }

    public void setMaxRecords(Integer maxRecords) {
        MaxRecords = maxRecords;
    }
}
