package com.example.bhw.Bean;

import jakarta.ejb.*;
import jakarta.jms.*;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "myQueue")
})
public class MDBean implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                System.out.println("Received message: " + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
