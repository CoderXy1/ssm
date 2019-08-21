package com.resource.service;

import com.resource.model.User;

import java.util.Date;
import java.util.List;

public interface IUserService {

    User selectUserByLogin(String userName,String userPassword);

    List<User> selectAllUsers();

    User selectUserById(int userId);

    int insertUser(int userId, String userName, String userPassword, String power, Date createDate,Date lastLoginTime,int loginTimes);

    int updateUser(int userId, String userName, String userPassword, String power, Date createDate,Date lastLoginTime,int loginTimes);

    int selectMaxUserId();

    int selectLoginTimes(int userId);

}
