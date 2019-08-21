package com.resource.service;

import com.resource.model.Book;
import java.util.Date;
import java.util.List;

public interface IBookService {

    Book selectBookById(int bookId);

    List<Book> selectBookByNameAndPage(String bookName, int pageIndex, int pageSize);

    List<Book> selectBookByName(String bookName);

    List<Book> selectBookByType(String typeName);

    int selectMaxBookId();

    int addBook(int bookId, String bookName, String bookPicPath, int bookScore, String author, String publishing, float price, int totalNumber, int havingNumber, String typeName, Date putInDate);

    int selectBookNum(String bookName);

}
