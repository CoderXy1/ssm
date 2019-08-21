package com.resource.service.impl;

import com.resource.dao.IUserDao;
import com.resource.model.User;
import com.resource.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User selectUserByLogin(String userName, String userPassword) {
        return this.userDao.selectUserByLogin(userName,userPassword);
    }

    @Override
    public List<User> selectAllUsers() {
        return this.userDao.selectAllUsers();
    }

    @Override
    public User selectUserById(int userId) {
        return this.userDao.selectUserById(userId);
    }

    @Override
    public int insertUser(int userId, String userName, String userPassword, String power, Date createDate, Date lastLoginTime, int loginTimes) {
        return this.userDao.insertUser(userId, userName, userPassword, power, createDate, lastLoginTime, loginTimes);
    }

    @Override
    public int updateUser(int userId, String userName, String userPassword, String power, Date createDate, Date lastLoginTime, int loginTimes) {
        return this.userDao.updateUser(userId, userName, userPassword, power, createDate, lastLoginTime, loginTimes);
    }

    @Override
    public int selectMaxUserId() {
        return this.userDao.selectMaxUserId();
    }

    @Override
    public int selectLoginTimes(int userId) {
        return this.userDao.selectLoginTimes(userId);
    }
}
