package com.edu.assistant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu_table")
@Data
public class Menu {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean status;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private boolean isActive;
    @JsonIgnore
    @OneToMany(mappedBy = "menu")
    private List<Order> order;
}
