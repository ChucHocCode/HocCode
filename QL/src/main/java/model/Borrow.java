package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="Borrow")


public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowId;
    private int readerId;
    private int bookCode;
    private Date borrowDate;
    private Date returnDate;
    private int quantity;
    private String readerName;

    public Borrow() {
    }

    public Borrow(int borrowId, int readerId, int bookCode, Date borrowDate, Date returnDate, int quantity, String readerName) {
        this.borrowId = borrowId;
        this.readerId = readerId;
        this.bookCode = bookCode;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.quantity = quantity;
        this.readerName = readerName;
    }

    public Borrow(int readerId, int bookCode, Date borrowDate,
                  Date returnDate, int quantity, String readerName) {
        this.readerId = readerId;
        this.bookCode = bookCode;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.quantity = quantity;
        this.readerName = readerName;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookCode() {
        return bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }


    @Override
    public String toString() {
        return "Borrow{" +
                "borrowId=" + borrowId +
                ", readerId=" + readerId +
                ", bookCode=" + bookCode +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", quantity=" + quantity +
                ", readerName='" + readerName + '\'' +
                '}';
    }
}
