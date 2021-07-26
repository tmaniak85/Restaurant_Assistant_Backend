package com.edu.assistant.service;

import com.edu.assistant.dao.MenuDao;
import com.edu.assistant.exception.NotFoundException;
import com.edu.assistant.model.Menu;
import com.edu.assistant.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    MenuDao menuDao;


    public List<Menu> showActiveDishes() {
        return menuDao.findAllByIsActiveIs(true);
    }

    public Menu addDishToMenu(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        menu.setPrice(menuDto.getPrice());
        menu.setStatus(true);
        menu.setActive(true);
        return menuDao.save(menu);
    }

    public Menu updateCurrentStatus(Long id) {
        Menu menu = menuDao.getById(id);
        if(menu.isStatus()) {
            menu.setStatus(false);
        } else {
            if(!menu.isStatus()) {
                menu.setStatus(true);
            }
        }
        return menuDao.save(menu);
    }

    public Menu deleteFromMenu(Long id) {
        Menu menu = menuDao.getById(id);
        menu.setActive(false);
        return menuDao.save(menu);
    }

    public Menu get(Long id) {
        return menuDao.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
    }

}
