package com.resource.controller;

import com.resource.jsoup.bookJsoup;
import com.resource.jsoup.movieJsoup;
import com.resource.model.Book;
import com.resource.model.Movie;
import com.resource.service.IBookService;
import com.resource.service.IMovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private IBookService bookService;

    @RequestMapping("/selectBookByNameAndPage")
    @ResponseBody
    public List<Book> selectBookByNameAndPage(@RequestParam("bookName")String bookName, @RequestParam("pageIndex")int pageIndex, @RequestParam("pageSize")int pageSize){

        return this.bookService.selectBookByNameAndPage(bookName, pageIndex, pageSize);

    }

    @RequestMapping("/selectBookNum")
    @ResponseBody
    public int selectBookNum(@RequestParam("bookName")String bookName) {
        return this.bookService.selectBookNum(bookName);
    }

    @RequestMapping("/selectBookByType")
    @ResponseBody
    public List<Book> selectBookByType(@RequestParam("typeName")String typeName) {
        return this.bookService.selectBookByType(typeName);
    }

    @RequestMapping("/addBookWithJsoup")
    @ResponseBody
    public int addBookWithJsoup() {
        List<Book> list = bookJsoup.getText();
        for (Book book:list){
            int id = this.selectMaxBookId() + 1;
            this.bookService.addBook(id, book.getBookName(), book.getBookPicPath(), book.getBookScore(), book.getAuthor(), book.getPublishing(), book.getPrice(), book.getTotalNumber(), book.getHavingNumber(), book.getTypeName(), book.getPutInDate());
            System.out.println(id);
        }
        return 1;
    }

    @RequestMapping("/selectMaxBookId")
    @ResponseBody
    public int selectMaxBookId() {
        return this.bookService.selectMaxBookId();
    }

}
