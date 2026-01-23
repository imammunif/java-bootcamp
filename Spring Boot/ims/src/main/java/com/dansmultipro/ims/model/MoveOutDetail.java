package com.dansmultipro.ims.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_move_out_detail")
public class MoveOutDetail extends BaseModel {

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "moveout_id", nullable = false)
    private MoveOut moveOut;

    public Integer getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public MoveOut getMoveOut() {
        return moveOut;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setMoveOut(MoveOut moveOut) {
        this.moveOut = moveOut;
    }

}
