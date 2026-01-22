package com.dansmultipro.tms.dto.piccustomer;

import java.util.UUID;

public class PicCustomerResponseDto {

    private UUID id;
    private String picName;
    private String customerName;

    public PicCustomerResponseDto(UUID id, String picName, String customerName) {
        this.id = id;
        this.picName = picName;
        this.customerName = customerName;
    }

    public UUID getId() {
        return id;
    }

    public String getPicName() {
        return picName;
    }

    public String getCustomerName() {
        return customerName;
    }

}
