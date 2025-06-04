package com.anand.event;

import com.anand.entity.Product;

public class ProductEvent {

    private String eventType;
    private Product product;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductEvent(String eventType, Product product) {
        this.eventType = eventType;
        this.product = product;
    }
    public  ProductEvent(){

    }
}
