package com.edu.assistant.service;

import com.edu.assistant.dto.DatesDto;
import com.edu.assistant.exception.BadDateFormatException;
import com.edu.assistant.model.Order;
import java.util.List;


public interface OrderService {


    Order findById(Long id);
    List<Order> findAllByStatusKitchen();
    List<Order> findAllByKitchenReadyStatus(String firstUsername, String secondUsername);
    List<Order> findAllByTablesIdAndWithoutArchivedStatus(Long firstId, Long secondId, Long thirdId);
    Order create(Order introducedOrder);
    void updateStatus(Order introducedOrder);
    List<Order> findAllByCreationDateTimeBetween(DatesDto dates) throws BadDateFormatException;

}
