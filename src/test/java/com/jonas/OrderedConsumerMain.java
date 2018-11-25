package com.jonas;

import com.jonsa.common.MqProp;
import com.jonsa.consumer.OrderedConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/11/26
 */
public class OrderedConsumerMain {

    public static void main(String[] args) {
        OrderedConsumer consumer = new OrderedConsumer();
        try {
            consumer.consume(MqProp.TOPIC_A, MqProp.TAG_A);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
