package com.dansmultipro.ims.dto;

import java.util.List;

public class PaginatedResponseDto<T> {

    private List<T> data;
    private Long total;

    public PaginatedResponseDto(List<T> data, Long total) {
        this.data = data;
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public Long getTotal() {
        return total;
    }

}
