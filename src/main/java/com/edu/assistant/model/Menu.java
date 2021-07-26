package com.edu.assistant.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
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
    private Double price;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MMMM-dd")
    private LocalDate creationDate;
    @Column(nullable = false)
    private boolean isActive;
    @JsonIgnore
    @OneToMany(mappedBy = "menu")
    private List<Order> order;

}
