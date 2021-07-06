package com.edu.assistant.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime creationDateTime;
    private OrderStatus status;
}
