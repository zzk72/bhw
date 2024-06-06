package com.example.bhw.Bean;

import com.example.bhw.Dao.OrdersDao;
import com.example.bhw.Dao.ServiceDao;
import com.example.bhw.Dao.ViewpointDao;
import com.example.bhw.Entity.Service;
import com.example.bhw.Entity.Viewpoint;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class QueueStatusBean {
    @Inject
    private ViewpointDao viewpointDao;
    @Inject
    private OrdersDao ordersDao;
    @Inject
    private ServiceDao serviceDao;
    public List<Viewpoint> getViewpoints() {
        return viewpointDao.getAllViewpoints();
    }
    public List<Service> getServicesByViewpointId(int viewpointId) {
        return serviceDao.getServicesByViewpointId(viewpointId);
    }
    public long getQueueStatus(Viewpoint viewpoint) {
        return ordersDao.getUncheckinOrdersCountByViewpointId(viewpoint.getId());
    }
}
