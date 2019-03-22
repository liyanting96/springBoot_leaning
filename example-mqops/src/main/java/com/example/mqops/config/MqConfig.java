package com.example.mqops.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ConfigurationProperties 注解语法类
 * 第一步：导入依赖 spring-boot-configuration-processor；
 * 第二步：把ConfigurationProperties注解修饰的类添加到Spring的IOC容器中；
 * 第三步：设置prefix属性，指定需要注入属性的前缀；
 * @Validated
 * 第四步：添加数据校验注解，开启数据校验；
 */

@Component
//@Configuration
//@PropertySource(value = {"classpath:/application-test.yml"}, encoding = "utf-8")
@ConfigurationProperties(prefix = "mq-config")
//@PropertySource(value = "application-test.yml")
public class MqConfig {
    // openflcon服务地址
    //@Value("${API_ADDR}")
    private String API_ADDR;
    // 服务单元
   //@Value("${SERVICE_NAME}")
    private String SERVICE_NAME;
   //@Value("${OWNER}")
    private String OWNER;
    // 配置的统一前缀
    //@Value("${PREFIX}")
    private String PREFIX;

    //@Value("{HEADERS}")
    private Map<String, String> HEADERS;

    private String hostGroupId;
    private String max_modify_count_per_day;

    public void setAPI_ADDR(String API_ADDR) {
        this.API_ADDR = API_ADDR;
    }

    public void setHEADERS(Map<String, String> HEADERS) {
        this.HEADERS = HEADERS;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
    }

    public void setPREFIX(String PREFIX) {
        this.PREFIX = PREFIX;
    }

    public void setSERVICE_NAME(String SERVICE_NAME) {
        this.SERVICE_NAME = SERVICE_NAME;
    }

    public void sethostGroupId(String HOSTGROUPID) {
        this.hostGroupId = HOSTGROUPID;
    }

    public void setMax_modify_count_per_day(String max_modify_count_per_day) {
        this.max_modify_count_per_day = max_modify_count_per_day;
    }

    public String getSERVICE_NAME() {
        return SERVICE_NAME;
    }

    public String getAPI_ADDR() {
        return API_ADDR;
    }

    public String getOWNER() {
        return OWNER;
    }

    public String getPREFIX() {
        return PREFIX;
    }

    public Map<String, String> getHEADERS() {
        return HEADERS;
    }

    public String gethostGroupId() {
        return hostGroupId;
    }

    public String getMax_modify_count_per_day() {
        return max_modify_count_per_day;
    }
    //    private String API_ADDR = System.getProperty("API_ADDR", "http://192.168.0.48:5100");
//    // openflcon组id
//    private int HOST_GROUP_ID = 8;
//    // 聚集后的数据组id
//     private int AGGREGATOR_HOST_GROUP_ID = 15;
//    // 告警地址
//     private String ALARM_ADDR = "http://172.16.116.48:6040";
//    // 查询用户部门信息的地址
//    // EHR_ADDRESS = "http://172.16.100.201:18080/service-web/service/accountAuth.do"
//    private String EHR_ADDRESS = "http://192.168.0.47:5200/service-web/service/accountAuth.do";
//    // 查询部门信息时使用的用户名密码
//    private String AES_KEY = "KL^5stThj_(js=sd";
//    private String AES_IV = "0102030405060708";
//    // 运维管理平台mysql地址
//    // 服务单元
//    private String SERVICE_NAME = "rocketmq.sunlands_mq.broker";
//    // OpsServer服务地址
//    private String OPS_SERVER = "http://172.16.116.48:9674";
//    // RocketMQ名称服务地址
//    private String MQ_NAMESVR = "172.16.116.48:9876";
//    // 用户权限管理服务地址
//    private String LOGIN_SERVER = "http://192.168.0.47:7388";
//    // 配置的统一前缀
//    private String PREFIX = "DEV_";
//    private String ENV_KEY = "ops测试环境";
//    private String NAME_PROXY = "http://172.16.116.48:8787/GetOneProxy";
}
