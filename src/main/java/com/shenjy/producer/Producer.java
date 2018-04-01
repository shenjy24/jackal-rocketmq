package com.shenjy.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 【生产者】
 *
 * @author shenjy 2017/11/17
 */
public class Producer {

    private static final String nameServerAddr = "127.0.0.1:9876";

    public void produce() throws MQClientException, InterruptedException{
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroup");
        producer.setNamesrvAddr(nameServerAddr);
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {
                Message msg = new Message("TopicA", "TagA", ("Hello RocketMq " + i).getBytes("UTF-8"));
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }

        }

        producer.shutdown();
    }
}
