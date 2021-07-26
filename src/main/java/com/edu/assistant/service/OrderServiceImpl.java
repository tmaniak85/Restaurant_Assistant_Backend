package com.edu.assistant.service;

import com.edu.assistant.dao.OrderDao;
import com.edu.assistant.exception.BadDateFormatException;
import com.edu.assistant.model.*;
import com.edu.assistant.dto.DatesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrderDao orderDao;


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
        return orderDao.findAllByTablesIdAndStatusEqualsOrTablesIdAndStatusEqualsOrTablesIdAndStatusEquals
                (firstId, OrderStatus.KITCHEN, secondId, OrderStatus.READY, thirdId, OrderStatus.RELEASED);
    }

    public Order create(Order introducedOrder) {
        return orderDao.save(introducedOrder);
    }

    public void updateStatus(Order introducedOrder) {
        orderDao.save(introducedOrder);
    }

    public List<Order> findAllByCreationDateTimeBetween(DatesDto dates) throws BadDateFormatException {
        if(dates == null || dates.getDateFrom() == null || dates.getDateTo() == null ||
                dates.getDateFrom().length() == 0 || dates.getDateTo().length() == 0) {
            throw new BadDateFormatException("C001-Data cannot be null");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateFrom = LocalDate.parse(dates.getDateFrom(), formatter);
            LocalDate dateTo = LocalDate.parse(dates.getDateTo(), formatter);
            return orderDao.findAllByCreationDateBetween(dateFrom, dateTo);
        }
    }

}
