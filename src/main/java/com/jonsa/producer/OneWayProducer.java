package com.jonsa.producer;

import com.jonsa.common.MqProp;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 【生产者：单向发送消息】
 *
 * @author shenjy 2017/11/17
 */
public class OneWayProducer {

    public void produce(String topic, String tag, String key) throws MQClientException, InterruptedException{
        DefaultMQProducer producer = new DefaultMQProducer(MqProp.PRODUCER_GROUP);
        producer.setNamesrvAddr(MqProp.NAME_SERVER_ADDR);
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {

                Message msg = new Message(topic, tag, key, ("Hello RocketMq " + i).getBytes("UTF-8"));
                producer.sendOneway(msg);

            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }

        }

        producer.shutdown();
    }
}
