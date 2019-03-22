package com.example.aop.exception;

import com.example.aop.common.RespCode;
import com.example.aop.common.RespEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

/**
 * 全局异常处理.
 * 一般情况下，方法都有异常处理机制，但不能排除有个别异常没有处理，导致返回到前台，因此在这里做一个异常拦截，统一处理那些未被处理过的异常
 * 当触发一个接口后，控制器调用服务类里面的方法saveUserInfo，saveUserInfo调用test1,test1抛出一个运行时异常。
 * 这时就会被@RestControllerAdvice注解的异常处理类接收到，这个类会根据
 * @ExceptionHandler指定的异常，映射到对应的方法中。
 */
@RestControllerAdvice
//@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 参数为空异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, ParamIsNullException.class})
    public RespEntity ParamMissOrIsNull(Exception ex){
        LOGGER.error("request param Exception:", ex);
        return RespEntity.error(RespCode.PARAM_ERROR.getCode(), "参数错误");
    }

    @ExceptionHandler(SqlException.class)
    public RespEntity SqlException(Exception ex){
        LOGGER.error("Sql Exception:", ex);
        return RespEntity.error(RespCode.SQL_ERROR.getCode(), "Sql内部异常");
    }
    @ExceptionHandler(SQLException.class)
    public RespEntity DataAccessException(Exception ex){

        LOGGER.error("SQLException:", ex);
        return RespEntity.error(RespCode.SQL_ERROR.getCode(), "Sql内部异常");
    }
//    @ExceptionHandler(SQLException.class)
//    public RespEntity SqlException(Exception ex){
//        LOGGER.error("Sql Exception:", ex);
//        return RespEntity.error(RespCode.SQL_ERROR.getCode(), "Sql内部异常");
//    }

}

