package com.example.demo.model;
import jakarta.persistence.*;




@Entity
@Table(name="Book")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookCode;
    private String bookName;
    private String authorName;
    private int publish;
    private String category;
    private int quantity;


    public Book() {
    }

    public Book(int bookCode, String bookName, String authorName, int publish, String category, int quantity) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publish = publish;
        this.category = category;
        this.quantity = quantity;
    }

    public int getBookCode() {
        return this.bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublish() {
        return this.publish;
    }

    public void setPublish(int publish) {
        this.publish = publish;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String toString() {
        return "Book{bookCode=" + this.bookCode + ", bookName='" + this.bookName + "', authorName='" + this.authorName + "', publish=" + this.publish + ", category='" + this.category + "', quantity=" + this.quantity + "}";
    }
}
