package com.resource.service.impl;

import com.resource.dao.IBookDao;
import com.resource.model.Book;
import com.resource.service.IBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("BookService")
public class BookServiceImpl implements IBookService {

    @Resource
    private IBookDao bookDao;

    @Override
    public Book selectBookById(int bookId) {
        return this.bookDao.selectBookById(bookId);
    }

    @Override
    public List<Book> selectBookByNameAndPage(String bookName, int pageIndex, int pageSize) {
        return this.bookDao.selectBookByNameAndPage(bookName, pageIndex, pageSize);
    }

    @Override
    public List<Book> selectBookByName(String bookName) {
        return this.bookDao.selectBookByName(bookName);
    }

    @Override
    public List<Book> selectBookByType(String typeName) {
        return this.bookDao.selectBookByType(typeName);
    }

    @Override
    public int selectMaxBookId() {
        return this.bookDao.selectMaxBookId();
    }

    @Override
    public int addBook(int bookId, String bookName, String bookPicPath, int bookScore, String author, String publishing, float price, int totalNumber, int havingNumber, String typeName, Date putInDate) {
        return this.bookDao.addBook(bookId, bookName, bookPicPath, bookScore, author, publishing, price, totalNumber, havingNumber, typeName, putInDate);
    }

    @Override
    public int selectBookNum(String bookName) {
        return this.bookDao.selectBookNum(bookName);
    }
}
