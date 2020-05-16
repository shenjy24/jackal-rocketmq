package com.jonas;

import com.jonas.common.MqProp;
import com.jonas.producer.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;

/**
 * Unit test for simple Application.
 */
public class ApplicationTest {

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
