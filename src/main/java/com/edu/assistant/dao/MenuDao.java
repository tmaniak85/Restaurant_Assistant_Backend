package com.edu.assistant.dao;

import com.edu.assistant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao extends JpaRepository<Menu, Long> {
    List<Menu> findAllByIsActiveIs(boolean isActive);
}
