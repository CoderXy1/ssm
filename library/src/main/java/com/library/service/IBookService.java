package com.library.service;

import com.library.model.Book;

import java.util.Date;
import java.util.List;

public interface IBookService {

    Book selectBookById(int bookId);

    List<Book> selectBookByNameAndPage(String bookName,int currPage,int pageSize);

    List<Book> selectBookByName(String bookName);

    List<Book> selectBookByType(int typeId);

    int selectMaxBookId();

    int addBook(int bookId, String bookName, String bookPicPath, int bookScore, String author, String publishing, float price, int totalNumber, int havingNumber, int typeId, Date putInDate);

    int deleteBook(int bookId);

    int updateBook(int bookId, String bookName, String bookPicPath, int bookScore, String author, String publishing, float price, int totalNumber, int havingNumber, int typeId, Date putInDate);

    int borrowBook(int bookId);

    int returnBook(int bookId);

}
