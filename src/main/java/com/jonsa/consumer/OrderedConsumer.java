package com.jonsa.consumer;

import com.jonsa.common.MqProp;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 【 顺序消息消费者 】
 *
 * @author shenjy 2018/11/25
 */
public class OrderedConsumer {

    public void consume(String topic, String tag) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqProp.CONSUMER_GROUP);
        consumer.setNamesrvAddr(MqProp.NAME_SERVER_ADDR);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(topic, "*");

        consumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt ext : msgs) {
                    try {
                        System.out.println(ext.getQueueId() + " : " + new String(ext.getBody(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
    }
}
