package model;

import java.util.Scanner;

public class Reader {
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

    public void inputdata() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Nhap ma nguoi doc: ");
            this.readerId = sc.nextInt();
        } while(this.readerId < 0);

        sc.nextLine();
        System.out.println("Nhap ten nguoi doc: ");
        this.fullName = sc.nextLine();
        System.out.println("Nhap ngay thang nam sinh : ");
        this.dob = sc.nextLine();
        System.out.println("Nhap gioi tinh :");
        this.gender = sc.nextLine();
        System.out.println("Nhap dia chi : ");
        this.address = sc.nextLine();
    }

    public String toString() {
        return "model.Reader{readerId=" + this.readerId + ", fullName='" + this.fullName + "', dob='" + this.dob + "', gender='" + this.gender + "', address='" + this.address + "'}";
    }
}
