package com.edu.assistant.service;

import com.edu.assistant.dao.OrderDao;
import com.edu.assistant.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;


    public List<Order> list() {
        return orderDao.findAll();
    }

    public Order findById(Long id) {
        return orderDao.findById(id).
                orElseThrow();
    }

    public List<Order> findAllByStatusKitchen() {
        return orderDao.findAllByStatusEquals(OrderStatus.KITCHEN);
    }

    public List<Order> findAllByKitchenReadyStatus(String firstUsername, String secondUsername) {
        return orderDao.findAllByUserUsernameAndStatusEqualsOrUserUsernameAndStatusEqualsOrderByStatusDesc(
                firstUsername, OrderStatus.KITCHEN, secondUsername, OrderStatus.READY);
    }

    public List<Order> findAllByTablesIdAndWithoutArchivedStatus(Long firstId, Long secondId, Long thirdId) {
        return orderDao.findAllByTablesIdAndStatusEqualsOrTablesIdAndStatusEqualsOrTablesIdAndStatusEquals(firstId, OrderStatus.KITCHEN, secondId, OrderStatus.READY, thirdId, OrderStatus.RELEASED);
    }

    public Order create(Order introducedOrder) {
        return orderDao.save(introducedOrder);
    }

    public void updateStatus(Order introducedOrder) {
        orderDao.save(introducedOrder);
    }
}
