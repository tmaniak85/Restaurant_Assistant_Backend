package com.edu.assistant.service;

import com.edu.assistant.model.Menu;
import com.edu.assistant.dto.MenuDto;
import java.util.List;


public interface MenuService {


    List<Menu> showActiveDishes();
    Menu addDishToMenu(MenuDto menuDto);
    Menu updateCurrentStatus(Long id);
    Menu deleteFromMenu(Long id);
    Menu get(Long id);

}
