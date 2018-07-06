package com.shenjy.consumer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.shenjy.common.MqProp;

/**
 * 【消费者启动类】
 *
 * @author shenjy 2017/11/16
 */
public class PushConsumerMain {
    public static void main(String[] args) {
        PushConsumer consumer = new PushConsumer();
        try {
            consumer.consume(MqProp.TOPIC_A, MqProp.TAG_A);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
