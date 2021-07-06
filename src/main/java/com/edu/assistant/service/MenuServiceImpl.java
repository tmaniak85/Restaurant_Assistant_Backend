package com.edu.assistant.service;

import com.edu.assistant.dao.MenuDao;
import com.edu.assistant.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;


    public List<Menu> list() {
        return menuDao.findAll();
    }

    public List<Menu> showActiveDishes() {
        return menuDao.findAllByIsActiveIs(true);
    }

    public Menu get(Long id) {
        return menuDao.findById(id).orElseThrow();
    }

    public Menu create(Menu introducedMenu) {
        Menu menu = new Menu();
        menu.setName(introducedMenu.getName());
        menu.setPrice(introducedMenu.getPrice());
        menu.setStatus(introducedMenu.isStatus());
        menu.setActive(true);
        return menuDao.save(menu);
    }

    public Menu updateCurrentStatus(Long id) {
        Menu menu = menuDao.getById(id);
        if(menu.isStatus() == true) {
            menu.setStatus(false);
        } else {
            if(menu.isStatus() == false) {
                menu.setStatus(true);
            }
        }
        return menuDao.save(menu);
    }

    public Menu setAsNonActive(Long id) {
        Menu menu = menuDao.getById(id);
        menu.setActive(false);
        return menuDao.save(menu);
    }
}
