package com.jonas.producer;

import com.jonas.common.MqProp;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * 【生产者：单向发送消息】
 *
 * @author shenjy 2017/11/17
 */
public class OneWayProducer {

    public void produce(String topic, String tag, String key, String content) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(MqProp.PRODUCER_GROUP);
        producer.setNamesrvAddr(MqProp.NAME_SERVER_ADDR);
        producer.start();

        try {

            Message msg = new Message(topic, tag, key, content.getBytes(StandardCharsets.UTF_8));
            producer.sendOneway(msg);

        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(1000);
        }

        producer.shutdown();
    }
}
