package com.jonas;

import com.jonsa.common.MqProp;
import com.jonsa.consumer.PushConsumer;
import com.jonsa.producer.AsyncProducer;
import com.jonsa.producer.OneWayProducer;
import com.jonsa.producer.SyncProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;

/**
 * Unit test for simple Application.
 */
public class ApplicationTest {

    @Test
    public void testConsume() {
        PushConsumer consumer = new PushConsumer();
        try {
            consumer.consume(MqProp.TOPIC_A, MqProp.TAG_A);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSyncSend() {
        SyncProducer producer = new SyncProducer();
        try {
            producer.produce(MqProp.TOPIC_A, MqProp.TAG_A, MqProp.KEY_A);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAsyncSend() {
        AsyncProducer producer = new AsyncProducer();
        try {
            producer.produce(MqProp.TOPIC_A, MqProp.TAG_A, MqProp.KEY_A);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOneWaySend() {
        OneWayProducer producer = new OneWayProducer();
        try {
            producer.produce(MqProp.TOPIC_A, MqProp.TAG_A, MqProp.KEY_A);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
