package com.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.model.User;
import com.library.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/selectUserByLogin")
    @ResponseBody
    public User selectUserByLogin(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword){

        return this.userService.selectUserByLogin(userName,userPassword);

    }

    @RequestMapping("/selectMaxId")
    @ResponseBody
    public int selectMaxId(){

        return this.userService.selectMaxId();

    }

    @RequestMapping("/addUser")
    @ResponseBody
    public int addUser(@RequestParam("userName")String userName, @RequestParam("userPassword")String userPassword,@RequestParam("sex") String sex,@RequestParam("phone") String phone,@RequestParam("address") String address, @RequestParam("power")String power) throws Exception{

        int userId = this.selectMaxId() + 1;
        sex = URLDecoder.decode(sex.toString(), "UTF-8");
        address = URLDecoder.decode(address.toString(), "UTF-8");
        power = URLDecoder.decode(power.toString(), "UTF-8");

        return this.userService.addUser(userId, userName, userPassword, sex, phone, address, power);

    }


}
