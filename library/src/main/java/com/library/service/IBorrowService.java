package com.library.service;

import com.library.model.Borrow;

import java.util.Date;
import java.util.List;

public interface IBorrowService {

    List<Borrow> selectAllBorrow();

    List<Borrow> selectBorrowByUserId(int userId);

    int updateBorrowReturnState(int borrowId,Date returnDate_reality);

    int updateBorrowRenewState(int borrowId,Date returnDate_should);

    int addBorrow(int borrowId,int userId,int bookId,Date borrowDate,Date returnDate_should,Date returnDate_reality,int renewTimes,String isExpire,String isReturn);

    int selectMaxBorrowId();
}
