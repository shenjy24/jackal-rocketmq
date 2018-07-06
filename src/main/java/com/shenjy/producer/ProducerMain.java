package com.shenjy.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.shenjy.common.MqProp;

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
            producer.produce(MqProp.TOPIC_A, MqProp.TAG_A, MqProp.KEY_A);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
