package com.edu.assistant.service;

import com.edu.assistant.dao.TablesDao;
import com.edu.assistant.model.TableStatus;
import com.edu.assistant.model.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TablesServiceImpl implements TablesService {

    @Autowired
    TablesDao tablesDao;


    public List list() {
        return tablesDao.findAll();
    }

    public Tables findById(Long id) {
        return tablesDao.findById(id)
                .orElseThrow();
    }

    public Tables create(Tables introducedTable) {
        Tables table = new Tables();
        table.setNumberOfTable(introducedTable.getNumberOfTable());
        table.setNumberOfSeats(introducedTable.getNumberOfSeats());
        table.setTableStatus(TableStatus.FREE);
        return tablesDao.save(table);
    }

    public void delete(Long id) {
        tablesDao.deleteById(id);
    }

    @Override
    public List<Tables> findOccupiedTablesByUserUsername(String username) {
        return tablesDao.findAllByUserUsername(username);
    }

    public void addTableToUser(Tables introducedTables) {
        Tables table = introducedTables;
        table.setUser(introducedTables.getUser());
        table.setTableStatus(TableStatus.OCCUPIED);
        tablesDao.save(table);
    }

    public void takeTableByWaiter(Tables introducedTables) {
        Tables table = introducedTables;
        table.setTableStatus(TableStatus.ORDER);
        tablesDao.save(table);
    }

    public void setTableAsFree(Tables introducedTables) {
        Tables table = introducedTables;
        table.setTableStatus(TableStatus.FREE);
        table.setUser(null);
        table.setOrder(null);
        tablesDao.save(table);
    }

}
