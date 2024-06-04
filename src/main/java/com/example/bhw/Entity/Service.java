package com.example.bhw.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type; // e.g., Restaurant, Medical Station, Gift Shop

    @ManyToOne
    @JoinColumn(name = "viewpoint_id", referencedColumnName = "id")
    private Viewpoint viewpoint;
}
