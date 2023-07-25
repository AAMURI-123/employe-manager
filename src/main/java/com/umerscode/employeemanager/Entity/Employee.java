package com.umerscode.employeemanager.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String jobTitle;
    @Column(unique = true)
    private String phone;
    private String imageUrl;
    @Column(unique = true, updatable = false, nullable = false)
    private String employeeCode;


    public Employee() {
    }

    public Employee( String name, String email, String jobTitle,
                    String phone, String imageUrl) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.imageUrl = imageUrl;

    }
}
