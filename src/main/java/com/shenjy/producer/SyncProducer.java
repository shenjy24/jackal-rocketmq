package com.shenjy.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.shenjy.common.MqProp;

/**
 * 【生产者：同步发送消息】
 *
 * @author shenjy 2017/11/17
 */
public class SyncProducer {

    public void produce(String topic, String tag, String key) throws MQClientException, InterruptedException{
        DefaultMQProducer producer = new DefaultMQProducer(MqProp.PRODUCER_GROUP);
        producer.setNamesrvAddr(MqProp.NAME_SERVER_ADDR);
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {
                if (i % 2 == 0) {
                    Message msg = new Message(topic, tag, key, ("Hello RocketMq " + i).getBytes("UTF-8"));
                    SendResult sendResult = producer.send(msg);
                    System.out.println(sendResult);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }

        }

        producer.shutdown();
    }
}
