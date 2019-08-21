package com.resource.model;

import java.util.Date;

public class Book {

    private int bookId;
    private String bookName;
    private String bookPicPath;
    private int bookScore;
    private String author;
    private String publishing;
    private float price;
    private int totalNumber;
    private int havingNumber;
    private String typeName;
    private Date putInDate;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPicPath() {
        return bookPicPath;
    }

    public void setBookPicPath(String bookPicPath) {
        this.bookPicPath = bookPicPath;
    }

    public int getBookScore() {
        return bookScore;
    }

    public void setBookScore(int bookScore) {
        this.bookScore = bookScore;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getHavingNumber() {
        return havingNumber;
    }

    public void setHavingNumber(int havingNumber) {
        this.havingNumber = havingNumber;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getPutInDate() {
        return putInDate;
    }

    public void setPutInDate(Date putInDate) {
        this.putInDate = putInDate;
    }
}
