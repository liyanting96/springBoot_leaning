package com.example.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
   * @Target(ElementType.PARAMETER) 该注解的作用目标为方法参数
   * @Target(ElementType.TYPE)   //接口、类、枚举、注解
   * @Target(ElementType.FIELD) //字段、枚举的常量
   * @Target(ElementType.METHOD) //方法
   * @Target(ElementType.PARAMETER) //方法参数
   * @Target(ElementType.CONSTRUCTOR)  //构造函数
   * @Target(ElementType.LOCAL_VARIABLE)//局部变量
   * @Target(ElementType.ANNOTATION_TYPE)//注解
   * @Target(ElementType.PACKAGE) ///包
   * @Retention 注解保留的位置
   * @Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
   * @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得
   * @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
*/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamCheck {
    /*
        是否非空，默认不能为空
    **/
    boolean notNull() default true;

}
