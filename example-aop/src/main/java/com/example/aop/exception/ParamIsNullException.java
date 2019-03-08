package com.example.aop.exception;

public class ParamIsNullException extends RuntimeException{

    private final String parameterName;
    public ParamIsNullException(String parameterName){
        super("");
        this.parameterName = parameterName;

    }

    @Override
    public String getMessage(){
        return "Required param: " + this.parameterName + " must be not null";
    }
    public final String getParameterName() {
        return this.parameterName;
    }

//    public RespEntity ParamIsNull(){
//        return RespEntity.error(RespCode.PARAM_ERROR.getCode(), "参数错误");
//    }
}
