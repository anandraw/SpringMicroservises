package com.anand.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "PRODUCT_QUERY")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private double price;

    @Version  // Ensures Hibernate versioning for optimistic locking
    private Integer version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }



    public Product(Long id, String name, String description, double price,Integer version) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.version = version;

    }

    public Product() {
    }
}
