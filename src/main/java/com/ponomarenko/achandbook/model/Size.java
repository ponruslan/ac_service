package com.ponomarenko.achandbook.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 5120, message = "Content too long (more than 5kB)")
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
