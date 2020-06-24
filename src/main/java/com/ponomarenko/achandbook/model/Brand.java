package com.ponomarenko.achandbook.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String filename;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private List<Product> products;
}
