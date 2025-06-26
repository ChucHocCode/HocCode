import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.*;

public class Readers {
    private int readerId;
    private String fullName;
    private String dob;
    private String gender;
    private String address;

    Readers(){};

    public Readers(int readerId, String fullName, String dob, String gender, String address) {
        this.readerId = readerId;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void inputdata(){
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("Nhap ma nguoi doc : ");
            readerId=sc.nextInt();
        }while(readerId <0);
        sc.nextLine();
        System.out.println("Nhap ten nguoi doc: ");
        fullName=sc.nextLine();
        System.out.println("Nhap ngay thang nam sinh : ");
        dob=sc.nextLine();
        System.out.println("Nhap gioi tinh: ");
        gender=sc.nextLine();
        System.out.println("Nhap dia chi : ");
        address=sc.nextLine();

    }

    @Override
    public String toString() {
        return
                "readerId=" + readerId +","+
                " fullName=" + fullName + "," +
                " dob='" + dob + "," +
                " gender='" + gender + "," +
                "address='" + address ;
    }
}
