package com.jonas.producer;

import com.jonas.common.MqProp;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * 【生产者：异步发送消息】
 *
 * @author shenjy 2017/11/17
 */
public class AsyncProducer {

    private DefaultMQProducer producer;

    public AsyncProducer() throws MQClientException {
        this.create();
    }

    public void create() throws MQClientException {
        producer = new DefaultMQProducer(MqProp.PRODUCER_GROUP);
        producer.setNamesrvAddr(MqProp.NAME_SERVER_ADDR);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
    }

    public void shutdown() {
        producer.shutdown();
    }

    public void produce(String topic, String tag, String key, String content) {

        try {
            Message msg = new Message(topic, tag, key, content.getBytes(StandardCharsets.UTF_8));

            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println("异步发送失败");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
