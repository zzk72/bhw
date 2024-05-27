package com.example.bhw.Dao;
import com.example.bhw.Entity.Orders;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrdersDao {
    @PersistenceContext
    private EntityManager em;

    public void insertOrder(Orders order) {
        em.persist(order);
    }

    public List<Orders> getOrders() {
        return em.createQuery("select o from Orders o", Orders.class)
                .getResultList();
    }

    public Orders getOrder(int id) {
        return em.createQuery("select o from Orders o where o.id = :id", Orders.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
