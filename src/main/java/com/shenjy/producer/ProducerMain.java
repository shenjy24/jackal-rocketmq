package com.shenjy.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;

/**
 * 【生产者启动类】
 *
 * @author shenjy 2017/11/17
 */
public class ProducerMain {
    public static void main(String[] args) {
        SyncProducer producer = new SyncProducer();
//        AsyncProducer producer = new AsyncProducer();
//        OneWayProducer producer = new OneWayProducer();
        try {
            producer.produce();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
