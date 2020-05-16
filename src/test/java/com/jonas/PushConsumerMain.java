package com.jonas;

import com.jonas.common.MqProp;
import com.jonas.consumer.PushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/11/26
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
