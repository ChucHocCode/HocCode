package model;

import java.util.Date;

public class Borrow {
    private int borrowId;
    private int readerId;
    private int bookCode;
    private Date borrowDate;
    private Date returnDate;

    public Borrow() {
    }

    public Borrow(int borrowId, int readerId, int bookCode, Date borrowDate, Date returnDate) {
        this.borrowId = borrowId;
        this.readerId = readerId;
        this.bookCode = bookCode;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getBorrowId() {
        return this.borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getReaderId() {
        return this.readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookCode() {
        return this.bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

    public Date getBorrowDate() {
        return this.borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
