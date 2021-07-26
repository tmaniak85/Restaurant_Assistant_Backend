package com.edu.assistant.service;

import com.edu.assistant.exception.BadCredentialsException;
import com.edu.assistant.exception.OrderInTableExistException;
import com.edu.assistant.exception.UserInTableExistException;
import com.edu.assistant.exception.UsernameExistException;
import com.edu.assistant.model.Tables;
import com.edu.assistant.dto.TablesDto;
import java.util.List;
import java.util.Map;


public interface TablesService {


    List<Tables> showTables();
    List<Tables> showOccupiedTablesForUser(String username);
    Tables createTable(TablesDto tablesDto) throws UsernameExistException;
    Tables findById(Long id);
    void deleteTable(Long id) throws UserInTableExistException;
    void addTableToUser(Long id, Map<String, Object> updates) throws BadCredentialsException;
    void takeTableByWaiter(Long id);
    void setTableAsFree(Long id) throws OrderInTableExistException;

}
