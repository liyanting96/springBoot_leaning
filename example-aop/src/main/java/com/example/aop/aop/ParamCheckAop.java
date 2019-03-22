package com.example.aop.aop;

import com.alibaba.fastjson.JSON;
import com.example.aop.annotations.ParamCheck;
import com.example.aop.exception.ParamIsNullException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/*
   * 把普通pojo实例化到spring容器中，相当于配置文件中的 <bean id="" class=""/>）
   * 泛指各种组件，就是说当我们的类不属于各种归类的时候（不属于@controller、@Services等的时候），
   * 我们就可以使用@Component来标注这个类。
 */

@Component
@Aspect
public class ParamCheckAop {
    private final Logger logger = LoggerFactory.getLogger("CtrollerLog");
    private HttpServletRequest request;

    /**
       * 定义一个切入点，范围为Controller包下的类
       * * execution：用于匹配执行方法的连接点，这是Spring AOP中国最主要的切入点指示符。该切入点的用法也相对复杂，execution表达式的格式如下：
       * execution(modifier-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
       * 上面的格式中，execution是不变的，用于作为execution表达式的开头，整个表达式中几个参数的详细解释如下：
       * modifier-pattern：指定方法的修饰符，支持通配符，该部分可以省略
       * ret-type-pattern：指定返回值类型，支持通配符，可以使用“*”来通配所有的返回值类型
       * declaring-type-pattern：指定方法所属的类，支持通配符，该部分可以省略
       * name-pattern：指定匹配的方法名，支持通配符，可以使用“*”来通配所有的方法名
       * param-pattern：指定方法的形参列表，支持两个通配符，“*”和“..”，其中“*”代表一个任意类型的参数，而“..”代表0个或多个任意类型的参数。
       * throw-pattern：指定方法声明抛出的异常，支持通配符，该部分可以省略
    * */
    @Pointcut("execution(public * com.example.aop.controller..*.*(..))")
    //使用一个返回值为void，方法体为空的方法来命名切入点，方法名即为切点名
    public void checkParam(){}

    @Before("checkParam()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        request = attributes.getRequest();
        System.out.println(request.getRequestURI());
        System.out.println(request.getMethod());
        System.out.println(request.getContextPath());
        System.out.println(request.getParameterMap().toString());
        logger.error("request info,  url: " + request.getRequestURI() + ",  param: " + JSON.toJSONString(request.getParameterMap()));
    }

    /**
     * 检查参数是否为空
     * 使用JoinPoint获取：Spring AOP提供使用org.aspectj.lang.JoinPoint类型获取连接点数据，
     * 任何通知方法的第一个参数都可以是JoinPoint(环绕通知是ProceedingJoinPoint，JoinPoint子类)
     * 连接点（Jointpoint）：表示需要在程序中插入横切关注点的扩展点，Spring只支持方法执行连接点，在AOP中表示为“在哪里干”；
     *
     * 1.throw用在方法体内，throw是语句抛出一个异常（对象）；throws是用在方法声明后面，表示再抛出异常，由该方法的调用者来处理。
     * 2.throw是具体向外抛异常的，抛出的是一个异常实例（代码中对应的有model）；throws声明了是哪种类型的异常，使它的调用者可以捕获这个异常。
     * 3.throw，如果执行了，那么一定是抛出了某种异常；而throws表示可能出现。
     * 4.同时出现的时候，throws出现在函数头，throw出现在函数体，两种不会由函数处理，真正的处理由函数的上层调用处理。
     */

    @Around("checkParam()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature signature = ((MethodSignature)pjp.getSignature());  // 返回当前连接点签名
        // 得到拦截的方法
        Method method = signature.getMethod();
        // 获取方法参数注解,返回二维数组是因为某些参数可能存在多个注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if(parameterAnnotations == null || parameterAnnotations.length == 0){
            // 在通知实现方法内部使用ProceedingJoinPoint的proceed()方法使目标方法执行
            return pjp.proceed();
        }
        // 获取方法参数名
        String[] paramNames = signature.getParameterNames();
        // 获取方法参数值
        Object[] paramValues = pjp.getArgs();
        // 获取方法参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        for(int i = 0; i < parameterAnnotations.length; i ++){
            for(int j = 0; j < parameterAnnotations[i].length; j ++){
                //如果该参数前面的注解是ParamCheck的实例，并且notNull()=true,则进行非空校验
                // instanceof 运算符是用来在运行时指出对象是否是特定类的一个实例。instanceof通过返回一个布尔值来指出，
                // 这个对象是否是这个特定类或者是它的子类的一个实例。
                if(parameterAnnotations[i][j] != null && parameterAnnotations[i][j] instanceof ParamCheck &&
                        ((ParamCheck)parameterAnnotations[i][j]).notNull()){
                    paramIsNull(paramNames[i], paramValues[i]);
                    break;
                }
            }
        }
        return pjp.proceed();

    }

    /**
     * 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     *
     * @param joinPoint
     */
    @AfterReturning("checkParam()")
    public void doAfterReturning(JoinPoint joinPoint) {
    }

    /**
     * 参数非空校验，如果参数为空，则抛出ParamIsNullException异常
     * @param paramName
     * @param paramValue
     */
    private void paramIsNull(String paramName, Object paramValue){
        if(paramValue == null || "".equals(paramValue.toString().trim())){
            throw new ParamIsNullException(paramName);
            //return RespEntity.error(RespCode.PARAM_ERROR.getCode(), "参数为空");
        }
    }








}
