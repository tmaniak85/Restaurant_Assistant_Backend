package com.edu.assistant.controller;

import com.edu.assistant.model.Menu;
import com.edu.assistant.model.Tables;
import com.edu.assistant.model.User;
import com.edu.assistant.service.TablesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tables")
@CrossOrigin
public class TablesController {

    @Autowired
    TablesServiceImpl tablesService;

    @GetMapping
    public List<Menu> showTables() {
        return this.tablesService.list();
    }

    @GetMapping("/{username}")
    public List<Tables> showOccupiedTablesForUser(@PathVariable(value = "username") String username) {
        return this.tablesService.findOccupiedTablesByUserUsername(username);
    }

    @PostMapping
    public Tables introduceTable(@RequestBody Tables table) {
        return tablesService.create(table);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable(value = "id") long id) {
        tablesService.delete(id);
    }

    @PatchMapping("/{id}")
    public void AddTableToUser(@PathVariable(value = "id") long id, @RequestBody Map<String, Object> updates) {
        Tables table = (Tables) tablesService.findById(id);
            User user = new User();
            table.setUser(user);
        table.getUser().setId(((Number) updates.get("id")).longValue());
        tablesService.addTableToUser(table);
    }

    @PatchMapping("/tableOrderStatus/{id}")
    public void takeTableByWaiter(@PathVariable(value = "id") long id) {
        Tables table = (Tables) tablesService.findById(id);
        tablesService.takeTableByWaiter(table);
    }

    @PatchMapping("/tableFreeStatus/{id}")
    public void setTableAsFree(@PathVariable(value = "id") long id) {
        Tables table = (Tables) tablesService.findById(id);
        tablesService.setTableAsFree(table);
    }
}
