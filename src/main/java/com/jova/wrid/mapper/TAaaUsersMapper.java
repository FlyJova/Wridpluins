package com.jova.wrid.mapper;


import com.jova.wrid.entity.TAaaUsers;

public interface TAaaUsersMapper {


    TAaaUsers selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(TAaaUsers users);
    int deleteByPrimaryKey(Long id);
    int insertSelective(TAaaUsers users);
}