package com.example.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="reader")
public class Reader {
    @Id
    @GeneratedValue(strategy=generationType.IDENTITY)
    private long Id;
    private int readerId;
    private String fullName;
    private String dob;
    private String gender;
    private String address;

    public Reader(){};

    public long getId(){
        return Id;
    }
    public void setId(long Id){
        this.Id=Id;
    }

    public int getReaderId(){
        return readerId;
    }
    public void setReaderId(int readerId){
        this.readerId=readerId;
    }

    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName=fullName;
    }

    public String getDob(){
        return dob;
    }
    public void setDob(String dob){
        this.dob=dob;
    }

    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender=gender;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "Id=" + Id +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
