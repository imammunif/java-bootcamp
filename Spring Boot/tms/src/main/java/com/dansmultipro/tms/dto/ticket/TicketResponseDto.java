package com.dansmultipro.tms.dto.ticket;

import java.time.LocalDate;

public class TicketResponseDto {

    private String id;
    private String code;
    private String title;
    private String description;
    private LocalDate expiredDate;
    private String statusName;
    private String customerName;
    private String productName;

    public TicketResponseDto(String id, String code, String title, String description, LocalDate expiredDate, String statusName, String customerName, String productName) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.expiredDate = expiredDate;
        this.statusName = statusName;
        this.customerName = customerName;
        this.productName = productName;
    }

    public String getId() {
        return id;
    }

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

    public String getStatusName() {
        return statusName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

}
