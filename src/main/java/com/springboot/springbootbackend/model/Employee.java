package com.springboot.springbootbackend.model;

// javax.persistence is now jakarta.persistence
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
// if @table annotation is not provided JPA provides table name same as the class name
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
}
