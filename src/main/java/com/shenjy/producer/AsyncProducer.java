package com.shenjy.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 【生产者：异步发送消息】
 *
 * @author shenjy 2017/11/17
 */
public class AsyncProducer {

    private static final String NAME_SERVER_ADDR = "127.0.0.1:9876";

    private static final String PRODUCER_GROUP = "SMS_GROUP";

    private static final String TOPIC = "TopicA";

    private static final String TAG = "TagA";

    private static final String KEY = "OrderId188";

    public void produce() throws MQClientException, InterruptedException{
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        for (int i = 0; i < 100; i++) {
            try {
                final int index = i;
                Message msg = new Message(TOPIC, TAG, KEY, ("Hello RocketMq " + i).getBytes("UTF-8"));

                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable e) {
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }

        }

        producer.shutdown();
    }
}
