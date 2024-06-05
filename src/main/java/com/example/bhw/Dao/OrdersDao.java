package com.example.bhw.Dao;

import com.example.bhw.Entity.Orders;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class OrdersDao {
    @PersistenceContext
    private EntityManager em;

    public void insertOrder(Orders order) {
        em.persist(order);
    }

    public List<Orders> getOrders() {
        return em.createQuery("select o from Orders o", Orders.class).getResultList();
    }

    public Orders getOrder(int id) {
        return em.createQuery("select o from Orders o where o.id = :id", Orders.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public long getUncheckinOrdersCountByViewpointId(int viewpointId) {
        return em.createQuery("SELECT COUNT(o) FROM Orders o WHERE o.viewpoint.id = :viewpointId AND o.status <> 'checked_in'", Long.class)
                .setParameter("viewpointId", viewpointId)
                .getSingleResult();
    }

    public void checkin(int orderId) {
        Orders order = getOrder(orderId);
        order.setStatus("checked");
        em.merge(order);
    }
    public List<Orders> getOrdersByUserId(int userId) {
        return em.createQuery("select o from Orders o where o.user.id = :userId", Orders.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public Orders getEarliestOrderAfterCurrentTime(int userId, int viewpointId, LocalDateTime currentTime) {
        Orders order = em.createQuery("select o from Orders o where o.user.id = :userId and o.viewpoint.id = :viewpointId  and o.status = 'unchecked' order by o.reservationTime", Orders.class)
                .setParameter("userId", userId)
                .setParameter("viewpointId", viewpointId)
//                .setParameter("currentTime", currentTime)
                .setMaxResults(1)
                .getSingleResult();
        return order;
    }
}
