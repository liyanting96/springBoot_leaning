package com.example.aop.common;

public enum RespCode {
//    SUCCESS(0, "请求成功"),
//    LOGICAL_ERROR(1, "session已经过期，请重新登录"),
//    SQL_ERROR(2, "内部异常"),
//    DATA_EXIST(3, "已经存在"),
//    PARAM_ERROR(4, "参数错误"),
//    NO_AUTH_ERROR(5, ""),
//    WARN(-1, "网络异常，请稍后重试");
    SUCCESS(0),
    LOGICAL_ERROR(1),
    SQL_ERROR(2),
    DATA_EXIST(3),
    PARAM_ERROR(4),
    NO_AUTH_ERROR(5),
    WARN(-1);

    private final  int code;
    RespCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

//    private String msg;
//
//    RespCode(int code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }
//
//    //根据枚举的code获取msg的方法
//    public static String getMsgByCode(int code){
//        for(RespCode respCode : RespCode.values()) {
//            if(respCode.getCode() == code){
//                return respCode.msg;
//            }
//        }
//        return null;
//    }
//
//
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
}
