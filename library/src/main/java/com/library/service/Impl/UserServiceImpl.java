package com.library.service.Impl;

import com.library.dao.IUserDao;
import com.library.model.User;
import com.library.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User selectUserById(int userId) {
        return this.userDao.selectUserById(userId);
    }

    @Override
    public User selectUserByLogin(String userName, String userPassword) {
        return this.userDao.selectUserByLogin(userName, userPassword);
    }

    @Override
    public List<User> selectAllUser() {
        return this.userDao.selectAllUser();
    }

    @Override
    public int selectMaxId() {
        return this.userDao.selectMaxId();
    }

    @Override
    public int addUser(int userId, String userName, String userPassword, String sex, String phone, String address, String power) {
        return this.userDao.addUser(userId, userName, userPassword, sex, phone, address, power);
    }

    @Override
    public int deleteUser(int userId) {
        return this.userDao.deleteUser(userId);
    }

    @Override
    public int updateUser(int userId, String userName, String userPassword, String sex, String phone, String address, String power) {
        return this.userDao.updateUser(userId, userName, userPassword, sex, phone, address, power);
    }
}
