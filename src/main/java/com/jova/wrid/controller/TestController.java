package com.jova.wrid.controller;

import com.jova.wrid.dao.UserDao;
import com.jova.wrid.entity.TAaaUsers;
import com.jova.wrid.mapper.TAaaUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/test")
    public  String getUser(@RequestParam(name = "id") Long usrId){
        TAaaUsers users = new TAaaUsers();
        users.setId(usrId);
        users.seteMail("xxxx_@yzf.com");
        users.setCreateTime(new Date());
        userDao.updateByPrimaryKeySelective(users);
        return  userDao.selectByPrimaryKey(usrId).toString();
    }
}
