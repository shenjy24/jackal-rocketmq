package com.shenjy.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.shenjy.common.MqProp;

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
