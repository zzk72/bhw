package com.example.bhw.JMSClient;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
@Stateless
public class MessageProducerBean {
    @Inject
    private JMSContext context;

    @Resource(name = "myQueue")
    private Queue queue;

    public void sendMessage(String message) {
        System.out.println("MessageProducerBean.sendMessage: " + message);
        context.createProducer().send(queue, message);
    }
}


