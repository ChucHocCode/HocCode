package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="Reader")


public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int readerId;
    private String fullName;
    private String dob;
    private String gender;
    private String address;

    public Reader() {
    }

    public Reader(int readerId, String fullName, String dob, String gender, String address) {
        this.readerId = readerId;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    public int getReaderId() {
        return this.readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "model.Reader{readerId=" + this.readerId + ", fullName='" + this.fullName + "', dob='" + this.dob + "', gender='" + this.gender + "', address='" + this.address + "'}";
    }
}
