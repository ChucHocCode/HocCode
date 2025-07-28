package com.example.Entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="book")


public class Borrow {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private long Id;
    private int borrowId;
    private int bookCode;
    private int readerId;
    private String readerName;
    private Date borrowDate;
    private Date returnDate;
    private int quantity;

    public Borrow(){};

    public long getId(){
        return Id;
    }
    public void setId(long Id){
        this.Id=Id;
    }

    public int getBorrowId(){
        return borrowId;
    }
    public void setBorrowId(int borrowId){
        this.borrowId=borrowId;
    }

    public int getBookCode(){
        return bookCode;
    }
    public void setBookCode(int bookCode){
        this.bookCode=bookCode;
    }

    public int getReaderId(){
        return readerId;
    }
    public void setReaderId(int readerId){
        this.readerId=readerId;
    }

    public String getReaderName(){
        return readerName;
    }
    public void setReaderName(String readerName){
        this.readerName=readerName;
    }

    public Date getBorrowDate(){
        return borrowDate;
    }
    public void setBorrowDate(Date borrowDate){
        this.borrowDate=borrowDate;
    }

    public Date getReturnDate(){
        return returnDate;
    }
    public void setReturnDate(Date returnDate){
        this.returnDate=returnDate;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }


    @Override
    public String toString() {
        return "Borrow{" +
                "Id=" + Id +
                ", borrowId=" + borrowId +
                ", bookCode=" + bookCode +
                ", readerId=" + readerId +
                ", readerName='" + readerName + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", quantity=" + quantity +
                '}';
    }
}
