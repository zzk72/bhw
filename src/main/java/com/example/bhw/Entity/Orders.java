package com.example.bhw.Entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "viewpoint_id", referencedColumnName = "id")
    private Viewpoint viewpoint;

    @Column(name = "status")
    private String status;
}
