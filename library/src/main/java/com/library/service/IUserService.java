package com.library.service;

import com.library.model.User;

import java.util.List;

public interface IUserService {

    User selectUserById(int userId);

    User selectUserByLogin(String userName, String userPassword);

    List<User> selectAllUser();

    int selectMaxId();

    int addUser(int userId, String userName, String userPassword, String sex, String phone, String address, String power);

    int deleteUser(int userId);

    int updateUser(int userId, String userName, String userPassword, String sex, String phone, String address, String power);

}
