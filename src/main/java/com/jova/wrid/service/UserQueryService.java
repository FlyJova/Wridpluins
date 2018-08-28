package com.jova.wrid.service;

import com.jova.wrid.entity.TAaaUsers;

public interface UserQueryService {
    TAaaUsers selectById(Long id);
}
