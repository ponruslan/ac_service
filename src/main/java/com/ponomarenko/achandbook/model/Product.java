package com.ponomarenko.achandbook.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название серии не может быть пустым")
    @Length(max = 255, message = "Имя слишком длинное")
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "error_id")
    private Error error;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "connect_id")
    private Connect connect;

    public String getErrorId(){
        if (error != null)
            return String.valueOf(error.getId());
        else return "нет листа";
    }

    public String getSizeFileName(){
        return size.getFilename();
    }
}
