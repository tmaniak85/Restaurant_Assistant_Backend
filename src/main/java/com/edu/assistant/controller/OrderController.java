package com.edu.assistant.controller;

import com.edu.assistant.model.*;
import com.edu.assistant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private TablesServiceImpl tablesService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MenuServiceImpl menuService;


    @GetMapping
    public List<Order> showOrders() {
        return this.orderService.list();
    }

    @GetMapping("/kitchen")
    public List<Order> findAllByStatusKitchen() {
        return this.orderService.findAllByStatusKitchen();
    }

    @GetMapping("/kitchenReadyStatus/{username}")
    public List<Order>findAllByKitchenReadyStatus(@PathVariable(value = "username") String firstUsername, @PathVariable(value = "username") String secondUsername, @PathVariable(value = "username") String thirdUsername) {
        return this.orderService.findAllByKitchenReadyStatus(firstUsername, secondUsername);
    }

    @PostMapping("/{tableId}")
    public Order createOrder(@PathVariable(value = "tableId") Long id, @RequestBody Order introducedOrder) {
        Order order = new Order();
        Tables table = this.tablesService.findById(id);
        order.setTables(table);
        User user = (User) this.userService.findById(table.getUser().getId());
        order.setUser(user);
        Menu menu = this.menuService.get(introducedOrder.getMenu().getId());
        order.setMenu(menu);
        order.setStatus(OrderStatus.KITCHEN);
        return orderService.create(order);
    }

    @PatchMapping("/ready/{id}")
    public void setOrderAsReady(@PathVariable(value = "id") Long id) {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.READY);
        orderService.updateStatus(order);
    }
    @PatchMapping("/released/{id}")
    public void setOrderAsReleased(@PathVariable(value = "id") Long id) {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.RELEASED);
        orderService.updateStatus(order);
    }
    @PatchMapping("/archived/{id}")
    public void setOrderAsArchived(@PathVariable(value = "id") Long id) {
        Order order = orderService.findById(id);
        order.setStatus(OrderStatus.ARCHIVED);
        orderService.updateStatus(order);
    }


    @GetMapping("/{tableId}")
    public List<Order> showOrdersByTableId(@PathVariable(value = "tableId") Long id) {
        return this.orderService.findAllByTablesIdAndWithoutArchivedStatus(id, id, id);
    }
//    @DeleteMapping("/{id}")
//    public void deleteMenu(@PathVariable(value = "id") long id) {
//        menuService.delete(id);
//    }
}
