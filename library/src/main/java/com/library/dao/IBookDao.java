package com.library.dao;

import com.library.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IBookDao {

    Book selectBookById(@Param("bookId") int bookId);

    List<Book> selectBookByNameAndPage(@Param("bookName") String bookName,@Param("currPage")int currPage,@Param("pageSize")int pageSize);

    List<Book> selectBookByName(@Param("bookName")String bookName);

    List<Book> selectBookByType(@Param("typeId") int typeId);

    int selectMaxBookId();

    int addBook(@Param("bookId") int bookId, @Param("bookName") String bookName, @Param("bookPicPath") String bookPicPath, @Param("bookScore") int bookScore, @Param("author") String author, @Param("publishing") String publishing, @Param("price") float price, @Param("totalNumber") int totalNumber, @Param("havingNumber") int havingNumber, @Param("typeId") int typeId, @Param("putInDate") Date putInDate);

    int deleteBook(@Param("bookId") int bookId);

    int updateBook(@Param("bookId") int bookId, @Param("bookName") String bookName, @Param("bookPicPath") String bookPicPath, @Param("bookScore") int bookScore, @Param("author") String author, @Param("publishing") String publishing, @Param("price") float price, @Param("totalNumber") int totalNumber, @Param("havingNumber") int havingNumber, @Param("typeId") int typeId, @Param("putInDate") Date putInDate);

    int borrowBook(@Param("bookId")int bookId);

    int returnBook(@Param("bookId")int bookId);

}
