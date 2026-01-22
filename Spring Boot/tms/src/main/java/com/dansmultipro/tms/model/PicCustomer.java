package com.dansmultipro.tms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_pic_customer")
public class PicCustomer extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "pic_id", nullable = false)
    private User pic;

    public User getCustomer() {
        return customer;
    }

    public User getPic() {
        return pic;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setPic(User pic) {
        this.pic = pic;
    }

}
