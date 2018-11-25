package com.jonsa.producer;

import com.jonsa.common.MqProp;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 【 顺序消息生产者 】
 *
 * @author shenjy 2018/11/25
 */
public class OrderedProducer {

    public void produce(String topic, String tag, String key) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(MqProp.PRODUCER_GROUP);
        producer.setNamesrvAddr(MqProp.NAME_SERVER_ADDR);
        producer.start();

        for (int i = 0; i < 100; i++) {
            int orderId = i % 10;
            try {
                Message msg = new Message(topic, tag, key, ("Hello RocketMq " + i).getBytes("UTF-8"));
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        producer.shutdown();
    }
}
