package com.edu.assistant.service;


import com.edu.assistant.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TablesService {
    public List<Tables> findOccupiedTablesByUserUsername(String username);
}
