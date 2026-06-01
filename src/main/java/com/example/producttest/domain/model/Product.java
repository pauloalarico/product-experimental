package com.example.producttest.domain.model;

import com.example.producttest.domain.enums.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "td_products")
public class Product {
    @Id
    @Column(name = "cd_identification")
    private UUID id;
    @Column(name = "nm_product")
    private String name;
    @Column(name = "tp_product")
    private ProductType type;
    @Column(name = "vl_price")
    private BigDecimal price;
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    public static Product of(String name, ProductType type, BigDecimal price, Integer stockQuantity) {
        Product product = new Product();
        product.id = null;
        product.name = name;
        product.type = type;
        product.price = price;
        product.stockQuantity = stockQuantity;
        return product;
    }

}
