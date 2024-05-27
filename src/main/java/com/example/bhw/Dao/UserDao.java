package com.example.bhw.Dao;

import com.example.bhw.Entity.Orders;
import com.example.bhw.Entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class UserDao {
    @PersistenceContext
    private EntityManager em;

    public User getUserById(int id) {
        return em.find(User.class, id);
    }
    public User getUserByName(String username) {
        return em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public void insertUser(User user) {
        em.persist(user);
    }
    public boolean isUserExist(String username) {
        return em.createQuery("select u from User u where u.username = :username")
                .setParameter("username", username)
                .getResultList()
                .size() > 0;
    }

    public int getUserId(String username) {
        return (int) em.createQuery("select u.id from User u where u.username = :username")
                .setParameter("username", username)
                .getSingleResult();
    }
    public List<Orders> getOrders(int userId) {
        return em.createQuery("select o from Orders o where o.user.id = :userId", Orders.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
