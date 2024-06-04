package com.example.bhw.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "viewpoint")
public class Viewpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "text")
    private String text;

    @Column(name = "image")
    private String image;
    @Column(name = "video")
    private String video;
    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    @Transient
    private long queueStatus;
}
