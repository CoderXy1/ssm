package com.resource.controller;

import com.resource.dao.IUserDao;
import com.resource.jsoup.bookJsoup;
import com.resource.model.Book;
import com.resource.model.User;
import com.resource.service.IBookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserDao userDao;

    @RequestMapping("/selectUserByLogin")
    @ResponseBody
    public User selectUserByLogin(@RequestParam("userName")String userName, @RequestParam("userPassword")String userPassword) {
        return this.userDao.selectUserByLogin(userName, userPassword);
    }

    @RequestMapping("/selectMaxUserId")
    @ResponseBody
    public int selectMaxUserId() {
        return this.userDao.selectMaxUserId();
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public int updateUser(@RequestParam("userId")int userId, @RequestParam("userName")String userName, @RequestParam("userPassword")String userPassword) {
        Date lastLoginTime = new Date();
        int loginTimes = this.selectLoginTimes(userId) + 1;
        return this.userDao.updateUser(userId, userName, userPassword, null, null, lastLoginTime, loginTimes);
    }

    @RequestMapping("/selectLoginTimes")
    @ResponseBody
    public int selectLoginTimes(@RequestParam("userId")int userId) {
        return this.userDao.selectLoginTimes(userId);
    }


}
