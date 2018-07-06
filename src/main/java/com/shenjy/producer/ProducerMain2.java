package com.shenjy.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.shenjy.common.MqProp;

/**
 * 【生产者启动类】
 *
 * @author shenjy 2017/11/17
 */
public class ProducerMain2 {
    public static void main(String[] args) {
        SyncProducer producer = new SyncProducer();
//        AsyncProducer producer = new AsyncProducer();
//        OneWayProducer producer = new OneWayProducer();
        try {
            producer.produce(MqProp.TOPIC_B, MqProp.TAG_B, MqProp.KEY_B);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
