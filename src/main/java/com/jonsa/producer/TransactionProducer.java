package com.jonsa.producer;

import com.jonsa.common.MqProp;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.concurrent.*;

/**
 * 【 顺序消息生产者 】
 *
 * @author shenjy 2018/11/25
 */
public class TransactionProducer {

    public void produce(String topic, String tag, String key) throws MQClientException, InterruptedException {
        TransactionListener transactionListener = new MyTransactionListener();
        TransactionMQProducer producer = new TransactionMQProducer(MqProp.PRODUCER_GROUP);
        producer.setNamesrvAddr(MqProp.NAME_SERVER_ADDR);

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });

        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {
                Message msg = new Message(topic, tag, key, ("Hello RocketMq " + i).getBytes("UTF-8"));
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.println(sendResult);

                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }

        producer.shutdown();
    }
}
