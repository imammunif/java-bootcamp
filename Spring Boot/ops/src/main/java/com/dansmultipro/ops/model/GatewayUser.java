package com.dansmultipro.ops.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_gateway_user")
public class GatewayUser extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "gateway_id", nullable = false)
    private Gateway gateway;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
