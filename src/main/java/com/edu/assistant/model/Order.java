package com.edu.assistant.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "order_table")
@Data
public class Order {


    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables tables;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MMMM-dd")
    private LocalDate creationDate;
    @CreationTimestamp
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime creationTime;
    private OrderStatus status;

}
