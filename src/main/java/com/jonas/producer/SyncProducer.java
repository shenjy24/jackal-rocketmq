package com.jonas.producer;

import com.jonas.common.MqProp;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

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
