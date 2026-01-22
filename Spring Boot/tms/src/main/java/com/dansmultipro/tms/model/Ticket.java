package com.dansmultipro.tms.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_ticket")
public class Ticket extends BaseModel {

    @Column(nullable = false, length = 5, unique = true)
    private String code;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate expiredDate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public User getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
