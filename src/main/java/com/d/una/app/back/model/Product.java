package com.d.una.app.back.model;


import com.d.una.app.back.model.enums.ProductTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(length = 100)
    private String name;
    @Column(length = 1500)
    private String description;
    @Column(length = 35)
    private Double price;
    @Enumerated(EnumType.STRING)
    @Column
    private ProductTypeEnum type;
}
