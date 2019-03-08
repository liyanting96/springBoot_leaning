package com.example.aop.common;

public class RespEntity {
    private int code;
    private String msg;
    private boolean ok;
    private Object data;

    private RespEntity(){}

    public RespEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RespEntity error(int code, String msg){
        RespEntity respEntity = new RespEntity();
        respEntity.setCode(code);
        respEntity.setMsg(msg);
        respEntity.setOk(false);
        return respEntity;
    }
    public static RespEntity error(boolean ok, String msg){
        RespEntity respEntity = new RespEntity();
        respEntity.setOk(ok);
        respEntity.setMsg(msg);
        return respEntity;
    }

    public static RespEntity success(){
        RespEntity respEntity = new RespEntity();
        respEntity.setCode(RespCode.SUCCESS.getCode());
        respEntity.setOk(true);
        respEntity.setMsg("success");
        return respEntity;
    }
    public static RespEntity success(boolean ok){
        RespEntity respEntity = new RespEntity();
        respEntity.setOk(ok);
        respEntity.setMsg("success");
        return respEntity;
    }

    public static RespEntity success(Object data){
        RespEntity respEntity = new RespEntity();
        respEntity.setCode(RespCode.SUCCESS.getCode());
        respEntity.setOk(true);
        respEntity.setMsg("success");
        respEntity.setData(data);
        return  respEntity;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public void setOk(boolean ok){
        this.ok = ok;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public boolean getOk(){ return ok; }
}
