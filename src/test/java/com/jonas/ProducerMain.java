package com.jonas;

import com.jonas.common.MqProp;
import com.jonas.producer.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;

import java.util.UUID;

/**
 * Unit test for simple Application.
 */
public class ProducerMain {

    @Test
    public void testTransactionSend() {
        TransactionProducer producer = new TransactionProducer();
        try {
            producer.produce(MqProp.TOPIC_A, MqProp.TAG_A, MqProp.KEY_A);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOrderedSend() {
        OrderedProducer producer = new OrderedProducer();
        try {
            producer.produce(MqProp.TOPIC_A, MqProp.TAG_A, MqProp.KEY_A);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSyncSend() {
        SyncProducer producer = new SyncProducer();
        try {
            String key = MqProp.KEY_A + UUID.randomUUID();
            producer.produce(MqProp.TOPIC_A, MqProp.TAG_A, key, "Test Sync Send");
        } catch (InterruptedException | MQClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAsyncSend() {
        try {
            AsyncProducer producer = new AsyncProducer();
            String key = MqProp.KEY_A + UUID.randomUUID();
            producer.produce(MqProp.TOPIC_A, MqProp.TAG_A, key, "Test Async Send");
            Thread.sleep(5000);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testOneWaySend() {
        OneWayProducer producer = new OneWayProducer();
        try {
            String key = MqProp.KEY_A + UUID.randomUUID();
            producer.produce(MqProp.TOPIC_A, MqProp.TAG_A, key, "Test OneWay Send");
        } catch (InterruptedException | MQClientException e) {
            e.printStackTrace();
        }
    }
}
