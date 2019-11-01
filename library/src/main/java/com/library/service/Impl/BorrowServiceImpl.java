package com.library.service.Impl;

import com.library.dao.IBorrowDao;
import com.library.model.Borrow;
import com.library.service.IBorrowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("borrowService")
public class BorrowServiceImpl implements IBorrowService {

    @Resource
    private IBorrowDao borrowDao;

    @Override
    public int selectMaxBorrowId() {
        return this.borrowDao.selectMaxBorrowId();
    }

    @Override
    public List<Borrow> selectAllBorrow() {
        return this.borrowDao.selectAllBorrow();
    }

    @Override
    public List<Borrow> selectBorrowByUserId(int userId) {
        return this.borrowDao.selectBorrowByUserId(userId);
    }

    @Override
    public int updateBorrowReturnState(int borrowId, Date returnDate_reality) {
        return this.borrowDao.updateBorrowReturnState(borrowId, returnDate_reality);
    }

    @Override
    public int updateBorrowRenewState(int borrowId, Date returnDate_should) {
        return this.borrowDao.updateBorrowRenewState(borrowId, returnDate_should);
    }

    @Override
    public int addBorrow(int borrowId, int userId, int bookId, Date borrowDate, Date returnDate_should, Date returnDate_reality, int renewTimes, String isExpire, String isReturn) {
        return this.borrowDao.addBorrow(borrowId, userId, bookId, borrowDate, returnDate_should, returnDate_reality, renewTimes, isExpire, isReturn);
    }
}
