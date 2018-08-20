package com.jova.wrid.dao;

import com.jova.wrid.entity.TAaaUsers;
import com.jova.wrid.mapper.TAaaUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private TAaaUsersMapper tAaaUsersMapper;

    public TAaaUsers selectByPrimaryKey(Long id){
        return tAaaUsersMapper.selectByPrimaryKey(id);
    }

      public int updateByPrimaryKeySelective(TAaaUsers users){
         return tAaaUsersMapper.updateByPrimaryKeySelective(users);
      }

    public int deleteByPrimaryKey(Long id){
        return tAaaUsersMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(TAaaUsers users){
        return tAaaUsersMapper.insertSelective(users);
    }

}
