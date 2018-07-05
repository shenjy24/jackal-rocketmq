package com.shenjy.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 【生产者：同步发送消息】
 *
 * @author shenjy 2017/11/17
 */
public class SyncProducer {

    private static final String NAME_SERVER_ADDR = "127.0.0.1:9876";

    private static final String PRODUCER_GROUP = "SMS_GROUP";

    private static final String TOPIC = "TopicA";

    private static final String TAG = "TagA";

    public void produce() throws MQClientException, InterruptedException{
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {
                Message msg = new Message(TOPIC, TAG, ("Hello RocketMq " + i).getBytes("UTF-8"));
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
