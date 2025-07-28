package com.example.Entity;

import jakarta.persistence.*;


@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private long Id;
    private int bookCode;
    private String bookName;
    private String authorName;
    private int publish;
    private String category;
    private int quantity;

    public Book(){};

    public long getId(){
        return Id;
    }
    public void setId(long Id){
        this.Id=Id;
    }

    public int getBookCode(){
        return bookCode;
    }
    public void setBookCode(int bookCode){
        this.bookCode=bookCode;
    }

    public String getBookName(){
        return bookName;
    }
    public void setBookName(String bookName){
        this.bookName=bookName;
    }

    public String getAuthorName(){
        return authorName;
    }
    public void setAuthorName(String authorName){
        this.authorName=authorName;

    }

    public int getPublish(){
        return publish;
    }
    public void setPublish(int publish){
        this.publish=publish;
    }

    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + Id +
                ", bookCode=" + bookCode +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", publish=" + publish +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
