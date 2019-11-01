package com.library.service;

import com.library.model.Ticket;

import java.util.Date;
import java.util.List;

public interface ITicketService {

    List<Ticket> selectAllTicket();

    List<Ticket> selectTicketByUserId(int userId);

    int updateReturnState(String isReturn,int bookId, int borrowId);


}
