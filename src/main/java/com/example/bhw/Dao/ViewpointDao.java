package com.example.bhw.Dao;

import com.example.bhw.Entity.Viewpoint;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ViewpointDao {
    @PersistenceContext
    private EntityManager em;

    public void insertViewpoint(Viewpoint viewpoint) {
        em.persist(viewpoint);
    }
    public List<Viewpoint> findAll() {
        return em.createQuery("select v from Viewpoint v", Viewpoint.class)
                .getResultList();
    }
    public Viewpoint getViewpoint(int id) {
        return em.createQuery("select v from Viewpoint v where v.id = :id", Viewpoint.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
