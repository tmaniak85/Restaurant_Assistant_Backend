package com.edu.assistant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tables_table")
@Data
public class Tables {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private long numberOfTable;
    private long numberOfSeats;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private TableStatus tableStatus;
    @JsonIgnore
    @OneToMany(mappedBy = "tables")
    private List<Order> order;
}
