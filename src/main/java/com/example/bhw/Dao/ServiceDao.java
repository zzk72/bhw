package com.example.bhw.Dao;

import com.example.bhw.Entity.Service;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ServiceDao {
    @PersistenceContext
    private EntityManager em;

    public List<Service> getServicesByViewpointId(int viewpointId) {
        return em.createQuery("SELECT s FROM Service s WHERE s.viewpoint.id = :viewpointId", Service.class)
                .setParameter("viewpointId", viewpointId)
                .getResultList();
    }
}
