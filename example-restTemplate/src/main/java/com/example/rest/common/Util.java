package com.example.rest.common;

import com.example.rest.config.MqConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Util {
    //# 1-机器 2-业务
//    private final static String payload = "{\n" +
//            "\t\"type\": 2,\n" +
//            "\t\"numerator\": \"$(producer.ts/topic=t2)\",\n" +
//            "\t\"denominator\": \"1\",\n" +
//            "\t\"desc\": \"xxxx\",\n" +
//            "\t\"output\": {\n" +
//            "\t\t\"metric\": \"agg.producer.ts\",\n" +
//            "\t\t\"tags\": \"topic=PRODUCT_TID_YZJTEST1\",\n" +
//            "\t\t\"type\": \"GAUGE\",\n" +
//            "\t\t\"step\": 60\n" +
//            "\t},\n" +
//            "\t\"serviceName\": 8\n" +
//            "}";
    @Autowired
    private Environment environment;

    @Autowired
    private MqConfig mqConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public RespEntity create_aggregator(Integer methodCode, String topicname, String cid){
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("type", 2);
        payload.put("numerator", "$(producer.ts/topic=t2)");
        payload.put("denominator", "1");
        payload.put("desc", "####");
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("metric", "agg.producer.ts");
        output.put("tags", "topic=PRODUCT_TID_YZJTEST1");
        output.put("type", "GAUGE");
        output.put("step", 60);
        payload.put("output", output);
        payload.put("serviceName", mqConfig.getSERVICE_NAME());

        MultiValueMap<String, Object> testMap = new LinkedMultiValueMap<>();
        testMap.add("type", 2);
        testMap.add("numerator", "$(producer.ts/topic=t2)");
        testMap.add("denominator", "1");
        testMap.add("desc", "####");
        testMap.add("output", output);
        testMap.add("serviceName", mqConfig.getSERVICE_NAME());
        System.out.println("testmap: " + testMap);


        Map<Integer, List<String>> content_tup = new HashMap<>();
        List<String> list1 = new ArrayList<String>();
        list1.add("$(producer.ts/topic=%s)");
        list1.add("topic=%s");
        list1.add("agg.producer.ts");
        list1.add("计算%s的生产tps");
        content_tup.put(0, list1);
        List<String> list2 = new ArrayList<String>();
        list2.add("$(topic.producer_offset/topic=%s)");
        list2.add("topic=%s");
        list2.add("agg.topic.producer_offset");
        list2.add("计算%s生产进度");
        content_tup.put(1, list2);
        List<String> list3 = new ArrayList<String>();
        list3.add("$sum(metric=consumer.ts topic=%s cid=.*)");
        list3.add("topic=%s");
        list3.add("agg.consumer.topic.ts");
        list3.add("计算%s的所有消费组消费tps和");
        content_tup.put(2, list3);
        List<String> list4 = new ArrayList<String>();
        list4.add("$(consumer.ts/cid=%s,topic=%s)");
        list4.add("cid=%s,topic=%s");
        list4.add("agg.consumer.ts");
        list4.add("计算%s的%s组消费tps");
        content_tup.put(3, list4);
        List<String> list5 = new ArrayList<String>();
        list5.add("$(consumer.offset.delay/cid=%s,topic=%s)");
        list5.add("cid=%s,topic=%s");
        list5.add("agg.consumer.delay");
        list5.add("计算%s的%s组消息堆积聚合");
        content_tup.put(4, list5);

        if(methodCode == null || topicname == null){
            return RespEntity.error(false,"parameter error");
        }
        if(methodCode >= 3 && cid == null){
            return RespEntity.error(false,"parameter error");
        }
//        if(methodCode < 3)
//            cid = "";
        if(methodCode == 0 || methodCode == 1 || methodCode == 2){
            payload.put("numerator", content_tup.get(methodCode).get(0) + " % topic");
            //Map<String, String> output1 = (Map<String, String>)payload.get("output");
            output.put("tags", content_tup.get(methodCode).get(1) + " % topic");
            output.put("metric", content_tup.get(methodCode).get(2));
            payload.put("output", output);
            payload.put("desc", content_tup.get(methodCode).get(3) + " % topic");
        }
        if(methodCode == 3 || methodCode == 4){
            payload.put("numerator", content_tup.get(methodCode).get(0) + " % (cid, topic)");
            //Map<String, String> output1 = (Map<String, String>)payload.get("output");
            output.put("tags", content_tup.get(methodCode).get(1) + " % (cid, topic)");
            output.put("metric", content_tup.get(methodCode).get(2));
            payload.put("output", output);
            payload.put("desc", content_tup.get(methodCode).get(3) + " % (cid, topic)");
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(payload);
            logger.info("create_aggregator request method is %s", json);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        // JSONObject jsonObject = new JSONObject(payload);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "lyt");
        // String url = mqConfig.getAPI_ADDR() + "/falconapi/service/aggregator";
        String url = "http://127.0.0.1:8000/jpa/getStudentByNameWithJson";
        String param = "?name=lyt";
        HttpHeaders httpHeaders = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        // cookies.add(environment.getProperty("Mqconfig.HEADERS"));
        String cookie = mqConfig.getHEADERS().get("cookie");
        cookies.add(cookie);
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        // 重点，不设置这个的话，postForObject会出现异常
        // org.springframework.web.client.HttpServerErrorException$InternalServerError: 500 null
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        httpHeaders.setContentType(type);
        //httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObject.toString(), httpHeaders);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        System.out.println(result);

//        MultiValueMap<String, Object> nameMap = new LinkedMultiValueMap<>();
//        nameMap.add("name", "lyt");
        Map<String, Object> nameHashMap = new HashMap<>();
        nameHashMap.put("name", "lyt");
        MultiValueMap<String, Object> nameMap = new LinkedMultiValueMap<>();
        nameMap.setAll(nameHashMap);
        String url1 = "http://127.0.0.1:8000/jpa/getStudentByName";
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, cookies);
        //MultiValueMap<String, Object> h = new LinkedMultiValueMap<>();
        //ResponseEntity<String> responseEntity
        HttpEntity<MultiValueMap<String, Object>> formEntity1 = new HttpEntity<>(nameMap, headers);
        String result1 = restTemplate.postForObject(url1, formEntity1, String.class);
        System.out.println(result1);



        return RespEntity.error(true,"parameter error");
    }
    public RespEntity create_aggregator(Integer methodCode, String topicname){
        return create_aggregator(methodCode, topicname, null);
    }


    //Map<Integer, List<String>>


//    @Autowired
//    private Environment environment;


//    public int checkTopic(String topicName){
//        String regex = "^[a-z0-9A-Z_]+$";
//        boolean match = topicName.matches(regex);
//        if(!match){
//            return RespCode.LOGICAL_ERROR.getCode();
//        }
//        String topic_prefix = "TID";
//        String env_prefix = environment.getProperty("Mqconfig.PREFIX");
//        int len = topic_prefix.length() + env_prefix.length();
//        String prefix = env_prefix + topic_prefix;
//        if(topicName.startsWith(prefix.substring(0,len))){
//            return RespCode.SUCCESS.getCode();
//        }
//        return RespCode.LOGICAL_ERROR.getCode();
//
//    }

//    public String getUser(HttpServletRequest request){
//
//
//    }
}
