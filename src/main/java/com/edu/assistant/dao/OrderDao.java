package com.edu.assistant.dao;

import com.edu.assistant.model.Order;
import com.edu.assistant.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface OrderDao extends JpaRepository<Order, Long> {


    List<Order> findAllByTablesIdAndStatusEqualsOrTablesIdAndStatusEqualsOrTablesIdAndStatusEquals
            (Long firstTableId, OrderStatus firstStatus, Long secondTableId, OrderStatus secondStatus,
             Long thirdTableId, OrderStatus thirdStatus);
    List<Order> findAllByStatusEquals(OrderStatus orderStatus);
    List<Order> findAllByUserUsernameAndStatusEqualsOrUserUsernameAndStatusEqualsOrderByStatusDesc
            (String firstUsername, OrderStatus firstStatus, String secondUsername, OrderStatus secondStatus);
    List<Order> findAllByCreationDateBetween(LocalDate dateFrom, LocalDate dateTo);

}
