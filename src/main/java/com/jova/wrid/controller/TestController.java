package com.jova.wrid.controller;

import com.jova.wrid.dao.UserDao;
import com.jova.wrid.entity.TAaaUsers;
import com.jova.wrid.mapper.TAaaUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
        users.seteMail("xxxx_@yzf.com_"+System.currentTimeMillis());
        users.setCreateTime(new Date());
        userDao.updateByPrimaryKeySelective(users);
        return  userDao.selectByPrimaryKey(usrId).toString();
    }

    @RequestMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id){


          userDao.deleteByPrimaryKey(id);
        return "success";
    }

    @RequestMapping("/insert")
    public  String insert(String userName){
        TAaaUsers users = new TAaaUsers();
        users.seteMail(userName);
        users.setPhoneNo("1111");
        users.setPassword("321654");
        users.setName("xiaoxiao_"+userName);
        userDao.insertSelective(users);
        return "success";
    }

}
