package com.library.dao;

import com.library.model.Ticket;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ITicketDao {

    List<Ticket> selectAllTicket();

    List<Ticket> selectTicketByUserId(@Param("userId")int userId);

    int updateReturnState(@Param("isReturn")String isReturn,@Param("bookId")int bookId, @Param("borrowId")int borrowId);

}
