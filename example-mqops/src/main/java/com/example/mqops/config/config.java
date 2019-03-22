package com.example.mqops.config;


public class config {
    public static final boolean threaded = true;
    public static final int DELETE_DELAY = 3600 * 24 * 7;
    public static final String OWNER = "user1";
    public static final String AGGREGATOR_HOST = "at.agg.su.instance.mq.rocketmq_broker";
    public static final String CONSOL_FUN = "AVERAGE";
    public static final int AGGREGATOR_HOST_GROUP_ID = 5;
    public static final int REQUEST_TIMEOUT = 10;
    public static final String NAME = System.getProperty("NAME", "breeze");
    public static final String SIG = System.getProperty("SIG", "default-token-used-in-server-side");
    public static final String LOG_LEVEL = System.getProperty("LOG_LEVEL", "DEBUG");

    // 服务单元
    public static final String SERVICE_NAME = "rocketmq.sunlands_mq.broker";
    public static final String CONSUMER_ALARM_NOTE = "auto_consumer_alarm";
    public static final String EXTRA_ALARM_USER = "";

}
