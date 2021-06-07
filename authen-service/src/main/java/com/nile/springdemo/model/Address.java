package com.nile.springdemo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="m_user_address")
public class Address implements Serializable {

    @Id
    @GenericGenerator(name = "uuid2" , strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY , generator = "uuid2")
    private String id;

    @Column(length = 120)
    private String line1;

    @Column(length = 120)
    private String line2;

    @Column(length = 120)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address() {
    }

    public Address(String line1, String line2, String zipcode) {
        this.line1 = line1;
        this.line2 = line2;
        this.zipcode = zipcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

