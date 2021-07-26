package com.edu.assistant.dao;

import com.edu.assistant.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TablesDao extends JpaRepository<Tables, Long> {


    List<Tables>findAllByUserUsername(String username);
    boolean existsTablesByNumberOfTable(Long id);

}
