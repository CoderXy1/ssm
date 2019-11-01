package com.library.dao;

import com.library.model.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IBorrowDao {

    List<Borrow> selectAllBorrow();

    List<Borrow> selectBorrowByUserId(@Param("userId")int userId);

    int selectMaxBorrowId();

    int updateBorrowReturnState(@Param("borrowId") int borrowId, @Param("returnDate_reality")Date returnDate_reality);

    int updateBorrowRenewState(@Param("borrowId")int borrowId,@Param("returnDate_should")Date returnDate_should);

    int addBorrow(@Param("borrowId")int borrowId,@Param("userId")int userId,@Param("bookId")int bookId,@Param("borrowDate")Date borrowDate,@Param("returnDate_should")Date returnDate_should,@Param("returnDate_reality")Date returnDate_reality,@Param("renewTimes")int renewTimes,@Param("isExpire")String isExpire,@Param("isReturn")String isReturn);


}
