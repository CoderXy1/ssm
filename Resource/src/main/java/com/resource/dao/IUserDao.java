package com.resource.dao;

import com.resource.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IUserDao {

    User selectUserByLogin(@Param("userName") String userName, @Param("userPassword")String userPassword);

    List<User> selectAllUsers();

    User selectUserById(@Param("userId")int userId);

    int insertUser(@Param("userId")int userId, @Param("userName")String userName, @Param("userPassword")String userPassword, @Param("power")String power, @Param("createDate")Date createDate, @Param("lastLoginTime")Date lastLoginTime, @Param("loginTimes")int loginTimes);

    int updateUser(@Param("userId")int userId, @Param("userName")String userName, @Param("userPassword")String userPassword, @Param("power")String power, @Param("createDate")Date createDate, @Param("lastLoginTime")Date lastLoginTime, @Param("loginTimes")int loginTimes);

    int selectMaxUserId();

    int selectLoginTimes(@Param("userId")int userId);
}
