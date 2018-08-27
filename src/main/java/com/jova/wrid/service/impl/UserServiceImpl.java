package com.jova.wrid.service.impl;

import com.jova.wrid.dao.UserDao;
import com.jova.wrid.entity.TAaaUsers;
import com.jova.wrid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public String updateAndSelect(Long usrId) {
        TAaaUsers users = new TAaaUsers();
        users.setId(usrId);
        users.seteMail("xxxx_@yzf.com_"+System.currentTimeMillis());
        users.setCreateTime(new Date());
        userDao.updateByPrimaryKeySelective(users);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.selectByPrimaryKey(usrId).toString();
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(TAaaUsers users) {
        return userDao.insertSelective(users);
    }
}
