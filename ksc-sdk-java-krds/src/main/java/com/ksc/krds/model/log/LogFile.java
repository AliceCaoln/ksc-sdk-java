package com.ksc.krds.model.log;

public class LogFile {

    String LogFileName;
    Double Size;
    Double RawSize;
    String Date;
    String StartTime;
    String EndTime;
    String SrcInstanceId;

    public String getLogFileName() {
        return LogFileName;
    }

    public void setLogFileName(String logFileName) {
        LogFileName = logFileName;
    }

    public Double getSize() {
        return Size;
    }

    public void setSize(Double size) {
        Size = size;
    }

    public Double getRawSize() {
        return RawSize;
    }

    public void setRawSize(Double rawSize) {
        RawSize = rawSize;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getSrcInstanceId() {
        return SrcInstanceId;
    }

    public void setSrcInstanceId(String srcInstanceId) {
        SrcInstanceId = srcInstanceId;
    }
}
