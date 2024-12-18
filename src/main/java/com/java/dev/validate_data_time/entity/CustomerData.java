package com.java.dev.validate_data_time.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "customer_data")
public class CustomerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "scheduletime")
    private Timestamp scheduletime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getScheduletime() {
        return scheduletime;
    }

    public void setScheduletime(Timestamp scheduletime) {
        this.scheduletime = scheduletime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerData that = (CustomerData) o;
        return id == that.id && Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name) && Objects.equals(email, that.email) && Objects.equals(gender, that.gender) && Objects.equals(scheduletime, that.scheduletime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, gender, scheduletime);
    }
}
