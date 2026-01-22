package com.dansmultipro.tms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_product_customer")
public class ProductCustomer extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public User getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
