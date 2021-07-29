package com.edu.assistant.service;

import com.edu.assistant.dao.TablesDao;
import com.edu.assistant.exception.*;
import com.edu.assistant.model.TableStatus;
import com.edu.assistant.model.Tables;
import com.edu.assistant.model.User;
import com.edu.assistant.dto.TablesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class TablesServiceImpl implements TablesService {


    @Autowired
    TablesDao tablesDao;


    public List<Tables> showTables() {
        return tablesDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Tables> showOccupiedTablesForUser(String username) {
        return tablesDao.findAllByUserUsername(username);
    }

    public Tables createTable(TablesDto tablesDto) throws UsernameExistException {
        if (this.tablesDao.existsTablesByNumberOfTable(tablesDto.getNumberOfTable())) {
            throw new UsernameExistException("C003-Table with this number exist");
        } else {
            Tables table = new Tables();
            table.setNumberOfTable(tablesDto.getNumberOfTable());
            table.setNumberOfSeats(tablesDto.getNumberOfSeats());
            table.setTableStatus(TableStatus.FREE);
            return tablesDao.save(table);
        }
    }

    public Tables findById(Long id) {
        return tablesDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found"));
    }

    public void deleteTable(Long id) throws UserInTableExistException {
        Tables table = tablesDao.getById(id);
        if(table.getUser() == null) {
            tablesDao.delete(table);
        } else {
            throw new UserInTableExistException("C006-Table has user field not empty-" + table.getUser().getUsername());
        }
    }

    public void addTableToUser(Long id, Map<String, Object> updates) throws BadCredentialsException {
        if(id == null) {
            throw new BadCredentialsException("C001-Data cannot be null");
        }
        Tables table = findById(id);
        User user = new User();
        if(updates.get("id") == null) {
            throw new BadCredentialsException("C002-Data cannot be null");
        }
        table.setUser(user);
        table.getUser().setId(((Number) updates.get("id")).longValue());
        table.setTableStatus(TableStatus.OCCUPIED);
        tablesDao.save(table);
    }

    public void takeTableByWaiter(Long id) {
        Tables table = findById(id);
        table.setTableStatus(TableStatus.ORDER);
        tablesDao.save(table);
    }

    public void setTableAsFree(Long id) throws OrderInTableExistException {
        Tables table = findById(id);
        if(table.getOrder().size() == 0 || table.getOrder() == null) {
            table.setTableStatus(TableStatus.FREE);
            table.setUser(null);
            table.setOrder(null);
            tablesDao.save(table);
        } else {
            throw new OrderInTableExistException("C001-Table includes active orders");
        }
    }

}
