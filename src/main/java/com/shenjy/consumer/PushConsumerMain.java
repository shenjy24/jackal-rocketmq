package com.shenjy.consumer;

import com.alibaba.rocketmq.client.exception.MQClientException;

/**
 * 【消费者启动类】
 *
 * @author shenjy 2017/11/16
 */
public class PushConsumerMain {
    public static void main(String[] args) {
        PushConsumer pushConsumer = new PushConsumer();
        try {
            pushConsumer.consume();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
