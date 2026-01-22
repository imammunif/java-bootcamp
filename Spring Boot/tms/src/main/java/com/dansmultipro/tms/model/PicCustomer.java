package com.dansmultipro.tms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_pic_customer")
public class PicCustomer extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User userCustomer;

    @ManyToOne
    @JoinColumn(name = "pic_id", nullable = false)
    private User UserPIC;

    public User getUserCustomer() {
        return userCustomer;
    }

    public User getUserPIC() {
        return UserPIC;
    }

    public void setUserCustomer(User userCustomer) {
        this.userCustomer = userCustomer;
    }

    public void setUserPIC(User userPIC) {
        UserPIC = userPIC;
    }

}
