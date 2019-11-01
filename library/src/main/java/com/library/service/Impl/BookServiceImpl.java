package com.library.service.Impl;

import com.library.dao.IBookDao;
import com.library.model.Book;
import com.library.service.IBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements IBookService {

    @Resource
    private IBookDao bookDao;

    @Override
    public Book selectBookById(int bookId) {
        return this.bookDao.selectBookById(bookId);
    }

    @Override
    public List<Book> selectBookByName(String bookName) {
        return this.bookDao.selectBookByName(bookName);
    }

    @Override
    public List<Book> selectBookByNameAndPage(String bookName,int currPage,int pageSize) {
        return this.bookDao.selectBookByNameAndPage(bookName, currPage, pageSize);
    }

    @Override
    public List<Book> selectBookByType(int typeId) {
        return this.bookDao.selectBookByType(typeId);
    }

    @Override
    public int selectMaxBookId() {
        return this.bookDao.selectMaxBookId();
    }

    @Override
    public int addBook(int bookId, String bookName, String bookPicPath, int bookScore, String author, String publishing, float price, int totalNumber, int havingNumber, int typeId, Date putInDate) {
        return this.bookDao.addBook(bookId, bookName, bookPicPath, bookScore, author, publishing, price, totalNumber, havingNumber, typeId, putInDate);
    }

    @Override
    public int deleteBook(int bookId) {
        return this.bookDao.deleteBook(bookId);
    }

    @Override
    public int updateBook(int bookId, String bookName, String bookPicPath, int bookScore, String author, String publishing, float price, int totalNumber, int havingNumber, int typeId, Date putInDate) {
        return this.bookDao.updateBook(bookId, bookName, bookPicPath, bookScore, author, publishing, price, totalNumber, havingNumber, typeId, putInDate);
    }

    @Override
    public int borrowBook(int bookId) {
        return this.bookDao.borrowBook(bookId);
    }

    @Override
    public int returnBook(int bookId) {
        return this.bookDao.returnBook(bookId);
    }
}
