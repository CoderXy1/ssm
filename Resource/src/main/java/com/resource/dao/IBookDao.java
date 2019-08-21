package com.resource.dao;

import org.apache.ibatis.annotations.Param;
import com.resource.model.Book;
import java.util.Date;
import java.util.List;

public interface IBookDao {

    Book selectBookById(@Param("bookId") int bookId);

    List<Book> selectBookByNameAndPage(@Param("bookName") String bookName, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    List<Book> selectBookByName(@Param("bookName") String bookName);

    List<Book> selectBookByType(@Param("typeName") String typeName);

    int selectMaxBookId();

    int addBook(@Param("bookId") int bookId, @Param("bookName") String bookName, @Param("bookPicPath") String bookPicPath, @Param("bookScore") int bookScore, @Param("author") String author, @Param("publishing") String publishing, @Param("price") float price, @Param("totalNumber") int totalNumber, @Param("havingNumber") int havingNumber, @Param("typeName")String typeName, @Param("putInDate") Date putInDate);

    int selectBookNum(@Param("bookName")String bookName);
}
