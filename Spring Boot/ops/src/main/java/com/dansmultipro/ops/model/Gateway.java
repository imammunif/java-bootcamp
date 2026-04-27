package com.dansmultipro.ops.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_m_gateway")
public class Gateway extends BaseModel {

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 10, unique = true)
    private String code;

}
