package com.nile.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name="m_user")
public class User {

    @Id
    @GenericGenerator(name = "uuid2" , strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY , generator = "uuid2")
    private String id;

    @Column(nullable = false , length = 120)
    private String name;

    @Column(nullable = false ,length = 120)
    private String password;

    @Column(nullable = false , unique = true , length = 60)
    private String email;

    // ลบ  contact ด้วยเมื่อ user ถูกลบ
    @OneToOne(mappedBy = "user" , orphanRemoval = true)
    private UserContact contact;

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER , orphanRemoval = true )
    private List<Address> addresses;

    public User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserContact getContact() {
        return contact;
    }

    public void setContact(UserContact contact) {
        this.contact = contact;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

