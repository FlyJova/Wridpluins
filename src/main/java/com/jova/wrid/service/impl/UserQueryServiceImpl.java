package com.jova.wrid.service.impl;

import com.jova.wrid.dao.UserDao;
import com.jova.wrid.entity.TAaaUsers;
import com.jova.wrid.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public TAaaUsers selectById(Long id) {
        return userDao.selectByPrimaryKey(id);
    }
}
