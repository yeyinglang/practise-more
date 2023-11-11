package org.example.netty.writeandflush;


public class ResponseSample {
    private final long currentTime;
    private String code;
    private String data;

    public ResponseSample(String ok, String data, long currentTimeMillis) {
        this.code = ok;
        this.data = data;
        this.currentTime = currentTimeMillis;


    }

    @Override
    public String toString() {
        return "responseData=[data=" + data + "+time=" + currentTime + "]";
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
