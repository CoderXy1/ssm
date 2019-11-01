package com.library.controller;

import com.library.model.Book;
import com.library.model.Borrow;
import com.library.service.IBookService;
import com.library.service.IBorrowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

    @Resource
    private IBorrowService borrowService;

    @RequestMapping("/addBorrow")
    @ResponseBody
    public int addBorrow(@RequestParam("userId")int userId,@RequestParam("bookId")int bookId) throws Exception{

        int borrowId = selectMaxBorrowId() + 1;
        Date borrowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);
        Date returnDate_should = cal.getTime();
        Date returnDate_reality = null;
        int renewTimes = 0;
        String isExpire = "false";
        String isReturn = "false";
        return this.borrowService.addBorrow(borrowId, userId, bookId, borrowDate, returnDate_should, returnDate_reality, renewTimes, isExpire, isReturn);
    }

    @RequestMapping("/selectBorrowByUserId")
    @ResponseBody
    public List<Borrow> selectBorrowByUserId(int userId){

        return this.borrowService.selectBorrowByUserId(userId);

    }

    @RequestMapping("/selectMaxBorrowId")
    @ResponseBody
    public int selectMaxBorrowId(){

        return this.borrowService.selectMaxBorrowId();

    }

    @RequestMapping("/updateBorrowRenewState")
    @ResponseBody
    public int updateBorrowRenewState(@RequestParam("borrowId")int borrowId,@RequestParam("returnDate_should")String returnDate_should) throws Exception{

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date returnDate_should_date = sf.parse(returnDate_should);
        System.out.println(returnDate_should);
        Calendar cal = Calendar.getInstance();
        cal.setTime(returnDate_should_date);
        cal.add(Calendar.DATE, 30);
        returnDate_should_date = cal.getTime();
        return this.borrowService.updateBorrowRenewState(borrowId,returnDate_should_date);

    }

    @RequestMapping("/updateBorrowReturnState")
    @ResponseBody
    public int updateBorrowReturnState(@RequestParam("borrowId")int borrowId) throws Exception{

        return this.borrowService.updateBorrowReturnState(borrowId,new Date());

    }



}
