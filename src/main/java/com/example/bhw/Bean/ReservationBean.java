package com.example.bhw.Bean;

import com.example.bhw.Dao.OrdersDao;
import com.example.bhw.Entity.Orders;
import com.example.bhw.Entity.User;
import com.example.bhw.Entity.Viewpoint;
import jakarta.ejb.AccessTimeout;
import jakarta.ejb.Stateful;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Stateful
public class ReservationBean {
    @Inject
    private OrdersDao ordersDao;
    @AccessTimeout(value = 5, unit = TimeUnit.SECONDS)
    public void reserveViewpoint(User user, Viewpoint viewpoint, LocalDateTime reservationTime) {
        Orders order = new Orders();
        order.setUser(user);
        order.setViewpoint(viewpoint);
        order.setReservationTime(reservationTime);
        order.setStatus("unchecked");

        ordersDao.insertOrder(order);
    }
}
