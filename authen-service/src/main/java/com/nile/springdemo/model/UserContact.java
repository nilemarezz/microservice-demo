package com.nile.springdemo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name="m_contact")
public class UserContact {
    @Id
    @GenericGenerator(name = "uuid2" , strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY , generator = "uuid2")
    private String id;

    @Column(length = 120)
    private String facebook;
    @Column(length = 120)
    private String telNo;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserContact() {
    }

    public UserContact(String facebook, String telNo  , User user) {
        this.facebook = facebook;
        this.telNo = telNo;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

