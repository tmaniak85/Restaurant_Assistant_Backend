package com.edu.assistant.controller;

import com.edu.assistant.exception.BadDateFormatException;
import com.edu.assistant.model.*;
import com.edu.assistant.service.*;
import com.edu.assistant.dto.DatesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private TablesService tablesService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MenuService menuService;


    @PreAuthorize("hasAuthority('CHEF')")
    @GetMapping("/kitchen")
    public List<Order> findAllByStatusKitchen() {
        return this.orderService.findAllByStatusKitchen();
    }

    @PreAuthorize("hasAuthority('WAITER')")
    @GetMapping("/kitchenReadyStatus/{username}")
    public List<Order>findAllByKitchenReadyStatusForUser(@PathVariable(value = "username") String firstUsername,
                                                         @PathVariable(value = "username") String secondUsername) {
        return this.orderService.findAllByKitchenReadyStatus(firstUsername, secondUsername);
    }

    @PreAuthorize("hasAuthority('WAITER')")
    @PostMapping("/{tableId}")
    public Order createOrder(@PathVariable(value = "tableId") Long id, @RequestBody Menu dish) {
        Order order = new Order();
        Tables table = this.tablesService.findById(id);
        order.setTables(table);
        User user = (User) this.userService.findById(table.getUser().getId());
        order.setUser(user);
        Menu menu = this.menuService.get(dish.getId());
        order.setMenu(menu);
        order.setStatus(OrderStatus.KITCHEN);
        return orderService.create(order);
    }

    @PreAuthorize("hasAuthority('CHEF')")
    @PatchMapping("/ready/{id}")
    public void setOrderAsReady(@PathVariable(value = "id") Long id) {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.READY);
        orderService.updateStatus(order);
    }

    @PreAuthorize("hasAuthority('WAITER')")
    @PatchMapping("/released/{id}")
    public void setOrderAsReleased(@PathVariable(value = "id") Long id) {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.RELEASED);
        orderService.updateStatus(order);
    }

    @PreAuthorize("hasAuthority('WAITER')")
    @PatchMapping("/archived/{id}")
    public void setOrderAsArchived(@PathVariable(value = "id") Long id) {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.ARCHIVED);
        order.setTables(null);
        orderService.updateStatus(order);
    }

    @PreAuthorize("hasAuthority('WAITER')")
    @GetMapping("/{tableId}")
    public List<Order> showOrdersByTableIdAndWithoutArchivedStatus(@PathVariable(value = "tableId") Long id) {
        return this.orderService.findAllByTablesIdAndWithoutArchivedStatus(id, id, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/stat")
    public List<Order> showOrdersBetweenDates(@RequestBody DatesDto dates) throws BadDateFormatException {
        return this.orderService.findAllByCreationDateTimeBetween(dates);
    }

}
