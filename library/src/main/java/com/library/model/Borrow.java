package com.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Borrow {

    private int borrowId;
    private int userId;
    private int bookId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date borrowDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date returnDate_should;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date returnDate_reality;
    private int renewTimes;
    private String isExpire;
    private String isReturn;

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate_should() {
        return returnDate_should;
    }

    public void setReturnDate_should(Date returnDate_should) {
        this.returnDate_should = returnDate_should;
    }

    public Date getReturnDate_reality() {
        return returnDate_reality;
    }

    public void setReturnDate_reality(Date returnDate_reality) {
        this.returnDate_reality = returnDate_reality;
    }

    public int getRenewTimes() {
        return renewTimes;
    }

    public void setRenewTimes(int renewTimes) {
        this.renewTimes = renewTimes;
    }

    public String getIsExpire() {
        return isExpire;
    }

    public void setIsExpire(String isExpire) {
        this.isExpire = isExpire;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }
}
