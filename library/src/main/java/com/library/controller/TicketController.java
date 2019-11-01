package com.library.controller;

import com.library.model.Book;
import com.library.model.Ticket;
import com.library.service.IBookService;
import com.library.service.ITicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Resource
    private ITicketService ticketService;

    @RequestMapping("/selectTicketByUserId")
    @ResponseBody
    public List<Ticket> selectTicketByUserId(@RequestParam("userId")int userId){

        return this.ticketService.selectTicketByUserId(userId);

    }

    @RequestMapping("/updateReturnState")
    @ResponseBody
    public int updateReturnState(@RequestParam("isReturn")String isReturn,@RequestParam("bookId")int bookId, @RequestParam("borrowId")int borrowId) throws Exception{

        return this.ticketService.updateReturnState(isReturn,bookId,borrowId);

    }




}
