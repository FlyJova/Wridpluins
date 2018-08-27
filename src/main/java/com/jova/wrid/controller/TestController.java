package com.jova.wrid.controller;


import com.jova.wrid.entity.TAaaUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    private com.jova.wrid.service.UserService userService;

    @RequestMapping("/updateAndSelect")
    public  String getUser(@RequestParam(name = "id") Long usrId){

      return userService.updateAndSelect(usrId);
    }

    @RequestMapping("/delete/{id}")
    public  String delete(@PathVariable("id") Long id){


        userService.deleteByPrimaryKey(id);
        return "success";
    }

    @RequestMapping("/insert")
    public  String insert(String userName){
        TAaaUsers users = new TAaaUsers();
        users.seteMail(userName);
        users.setPhoneNo("1111");
        users.setPassword("321654");
        users.setName("xiaoxiao_"+userName);
        userService.insertSelective(users);
        return "success";
    }

}
