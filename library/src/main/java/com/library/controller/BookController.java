package com.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.model.Book;
import com.library.service.IBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private IBookService bookService;

    @RequestMapping("/selectBookByNameAndPage")
    @ResponseBody
    public List<Book> selectBookByNameAndPage(@RequestParam("bookName") String bookName,@RequestParam("currPage") int currPage,@RequestParam("pageSize") int pageSize) throws Exception{

        List<Book> list= this.bookService.selectBookByNameAndPage(bookName, currPage, pageSize);

        return list;
    }

    @RequestMapping("/selectBookByName")
    @ResponseBody
    public List<Book> selectBookByName(@RequestParam("bookName") String bookName) throws Exception{

        return this.bookService.selectBookByName(bookName);

    }

    @RequestMapping("/selectBookById")
    @ResponseBody
    public Book selectBookById(@RequestParam("bookId") int bookId) throws Exception{

        return this.bookService.selectBookById(bookId);

    }

    @RequestMapping("/addBook")
    @ResponseBody
    public int addBook(@RequestParam("bookName")String bookName, @RequestParam("bookPicPath")String bookPicPath,@RequestParam("bookScore") int bookScore,@RequestParam("author") String author,@RequestParam("publishing") String publishing,@RequestParam("price") float price,@RequestParam("totalNumber") int totalNumber,@RequestParam("havingNumber") int havingNumber, @RequestParam("typeId")int typeId) throws Exception{

        int bookId = this.selectMaxBookId() + 1;
        Date putInDate = new Date();
        bookName = URLDecoder.decode(bookName.toString(), "UTF-8");
        bookPicPath = URLDecoder.decode(bookPicPath.toString(), "UTF-8");
        author = URLDecoder.decode(author.toString(), "UTF-8");
        publishing = URLDecoder.decode(publishing.toString(), "UTF-8");
        return this.bookService.addBook(bookId, bookName, bookPicPath, bookScore, author, publishing, price, totalNumber, havingNumber, typeId, putInDate);

    }

    @RequestMapping("/selectMaxBookId")
    @ResponseBody
    public int selectMaxBookId(){

        return this.bookService.selectMaxBookId();

    }

    @RequestMapping("/borrowBook")
    @ResponseBody
    public int borrowBook(@RequestParam("bookId")int bookId){

        return this.bookService.borrowBook(bookId);

    }

    @RequestMapping("/returnBook")
    @ResponseBody
    public int returnBook(@RequestParam("bookId")int bookId){

        return this.bookService.returnBook(bookId);

    }




}
