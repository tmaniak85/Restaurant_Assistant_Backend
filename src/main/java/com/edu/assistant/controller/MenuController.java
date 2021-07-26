package com.edu.assistant.controller;

import com.edu.assistant.model.Menu;
import com.edu.assistant.service.MenuService;
import com.edu.assistant.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/menu")
@CrossOrigin("http://localhost:4200")
public class MenuController {


    @Autowired
    private MenuService menuService;


    @GetMapping("/showActiveDishes")
    public List<Menu> showActiveDishes() {
        return this.menuService.showActiveDishes();
    }

    @PreAuthorize("hasAuthority('CHEF')")
    @PostMapping
    public Menu addDishToMenu(@Valid @RequestBody MenuDto menuDto) {
        return menuService.addDishToMenu(menuDto);
    }

    @PreAuthorize("hasAuthority('CHEF')")
    @PutMapping("/updateCurrentStatus/{id}")
    public Menu updateCurrentStatus(@PathVariable(value = "id") long id) {
        return menuService.updateCurrentStatus(id);
    }

    @PreAuthorize("hasAuthority('CHEF')")
    @PutMapping("/deleteFromMenu/{id}")
    public Menu deleteFromMenu(@PathVariable(value = "id") long id) {
        return menuService.deleteFromMenu(id);
    }

}
