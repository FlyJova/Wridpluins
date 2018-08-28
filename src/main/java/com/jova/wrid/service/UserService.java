package com.jova.wrid.service;

import com.jova.wrid.entity.TAaaUsers;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    String updateAndSelect(Long usrId);

    @Transactional
    String updateAndSelectNoT(Long usrId);

    public int deleteByPrimaryKey(Long id);
    public int insertSelective(TAaaUsers users);

}
