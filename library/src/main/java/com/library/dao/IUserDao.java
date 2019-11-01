package com.library.dao;

import com.library.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {

    User selectUserById(@Param("userId") int userId);

    User selectUserByLogin(@Param("userName") String userName, @Param("userPassword") String userPassword);

    List<User> selectAllUser();

    int selectMaxId();

    int addUser(@Param("userId") int userId, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("sex") String sex, @Param("phone") String phone, @Param("address") String address, @Param("power") String power);

    int deleteUser(@Param("userId") int userId);

    int updateUser(@Param("userId") int userId, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("sex") String sex, @Param("phone") String phone, @Param("address") String address, @Param("power") String power);

}
