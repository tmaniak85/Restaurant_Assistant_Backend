package com.edu.assistant.controller;

import com.edu.assistant.model.Menu;
import com.edu.assistant.service.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;


    @GetMapping("/showActiveDishes")
    public List<Menu> showActiveDishes() {
        return this.menuService.showActiveDishes();
    }

    @PostMapping
    public Menu introduceMenu(@RequestBody Menu menu) {
        return menuService.create(menu);
    }

    @PutMapping("/updateCurrentStatus/{id}")
    public Menu updateCurrentStatus(@PathVariable(value = "id") long id) {
        return menuService.updateCurrentStatus(id);
    }

    @PutMapping("/setAsNonActive/{id}")
    public Menu setAsNonActive(@PathVariable(value = "id") long id) {
        return menuService.setAsNonActive(id);
    }
}
