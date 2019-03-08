package com.example.aop.common;

public enum AggregatorService {
    ProducerTs(0),
    TopicOffset(1),
    ConsumerSumTs(2),
    ConsumerTs(3),
    ConsumerDelayTs(4);

    private final Integer method;

    AggregatorService(Integer method){
        this.method = method;
    }

    public Integer getMethod(){
        return method;
    }
}
