package com.ponomarenko.acservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private String filename;

    @OneToMany
    @JoinColumn(name = "size_id")
    private List<Product> products;

    @Override
    public String toString() {
        return id.toString();
    }
}
