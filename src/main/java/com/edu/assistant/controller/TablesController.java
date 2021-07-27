package com.edu.assistant.controller;

import com.edu.assistant.exception.BadCredentialsException;
import com.edu.assistant.exception.OrderInTableExistException;
import com.edu.assistant.exception.UserInTableExistException;
import com.edu.assistant.exception.UsernameExistException;
import com.edu.assistant.model.Tables;
import com.edu.assistant.service.TablesService;
import com.edu.assistant.dto.TablesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/tables")
@CrossOrigin
public class TablesController {


    @Autowired
    TablesService tablesService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Tables> showTables() {
        return this.tablesService.showTables();
    }

    @PreAuthorize("hasAuthority('WAITER')")
    @GetMapping("/{username}")
    public List<Tables> showOccupiedTablesForUser(@PathVariable(value = "username") String username) {
        return this.tablesService.showOccupiedTablesForUser(username);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Tables createTable(@Valid @RequestBody TablesDto tablesDto) throws UsernameExistException {
        return tablesService.createTable(tablesDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable(value = "id") long id) throws UserInTableExistException {
        tablesService.deleteTable(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public void addTableToUser(@PathVariable(value = "id") Long id, @RequestBody Map<String, Object> updates)
            throws BadCredentialsException {
        tablesService.addTableToUser(id, updates);
    }

    @PreAuthorize("hasAuthority('WAITER')")
    @PatchMapping("/tableOrderStatus/{id}")
    public void takeTableByWaiter(@PathVariable(value = "id") long id) {
        tablesService.takeTableByWaiter(id);
    }


    @PreAuthorize("hasAuthority('WAITER')")
    @PatchMapping("/tableFreeStatus/{id}")
    public void setTableAsFree(@PathVariable(value = "id") long id) throws OrderInTableExistException {
        tablesService.setTableAsFree(id);
    }
}
