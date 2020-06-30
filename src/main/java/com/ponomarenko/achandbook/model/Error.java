package com.ponomarenko.achandbook.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table (name = "errors")
public class Error {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition="LONGTEXT")
    private String content;

    @OneToMany
    @JoinColumn(name = "error_id")
    private List<Product> products;

    @Override
    public String toString() {
        return name;
    }
}
