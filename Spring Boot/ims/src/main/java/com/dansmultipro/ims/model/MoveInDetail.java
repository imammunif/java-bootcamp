package com.dansmultipro.ims.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_move_in_detail")
public class MoveInDetail extends BaseModel {

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "movein_id", nullable = false)
    private MoveIn moveIn;

    public Integer getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public MoveIn getMoveIn() {
        return moveIn;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setMoveIn(MoveIn moveIn) {
        this.moveIn = moveIn;
    }

}
