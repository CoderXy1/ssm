package com.library.service.Impl;

import com.library.dao.ITicketDao;
import com.library.model.Ticket;
import com.library.service.ITicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("ticketService")
public class TicketServiceImpl implements ITicketService {

    @Resource
    private ITicketDao ticketDao;

    @Override
    public List<Ticket> selectAllTicket() {
        return this.ticketDao.selectAllTicket();
    }

    @Override
    public List<Ticket> selectTicketByUserId(int userId) {
        return this.ticketDao.selectTicketByUserId(userId);
    }

    @Override
    public int updateReturnState(String isReturn,int bookId, int borrowId) {
        return this.ticketDao.updateReturnState(isReturn,bookId,borrowId);
    }
}
