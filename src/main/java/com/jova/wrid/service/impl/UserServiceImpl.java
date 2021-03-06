package com.jova.wrid.service.impl;

import com.jova.wrid.dao.UserDao;
import com.jova.wrid.entity.TAaaUsers;
import com.jova.wrid.service.UserQueryService;
import com.jova.wrid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserQueryService userQueryService;

    /**
     * 有事务的时候  且查询参与事务时候 以事务中的数据库connection为准 主动切换数据源无效
     * @param usrId
     * @return
     */
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


    /**
     * 无事务的时候  则切换数据源成功
     * @param usrId
     * @return
     */
    @Override
    public String updateAndSelectNoT(Long usrId) {
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

    /**
     * 查询不参与事务，则切换数据源成功
     * 如果一定要查询从库，则查询不要参与事务（ @Transactional(propagation = Propagation.NOT_SUPPORTED)）
     * @param usrId
     * @return
     */
    @Transactional
    @Override
    public String updateAndSelectNotS(Long usrId) {
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
        return userQueryService.selectById(usrId).toString();
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
