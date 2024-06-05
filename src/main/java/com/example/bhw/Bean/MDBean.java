//package com.example.bhw.Bean;
//
//import com.example.bhw.Dao.OrdersDao;
//import jakarta.ejb.*;
//import jakarta.jms.*;
//import jakarta.inject.Inject;
//import org.json.JSONObject;
//
//
//@MessageDriven(activationConfig = {
//        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
//        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "myQueue")
//})
//public class MDBean implements MessageListener {
//
//    @Inject
//    private OrdersDao ordersDao;
//
//    @Override
//    public void onMessage(Message message) {
//        if (message instanceof TextMessage) {
//            try {
//                String text = ((TextMessage) message).getText();
//                System.out.println("Received message: " + text);
//
//                // 解析JSON消息
//                JSONObject jsonObject = new JSONObject(text);
//                int OrderId = jsonObject.getInt("orderId");
//                if(OrderId == -1) {
//                    return;
//                }
//                // 执行checkin操作
//                checkin(OrderId);
//
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void checkin(int orderId) {
//        // 假设OrdersDao有一个checkin方法来处理checkin操作
//        ordersDao.checkin(orderId);
//    }
//}
