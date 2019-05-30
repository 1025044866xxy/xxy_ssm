package com.xxy.service.Impl;

import com.xxy.dao.RootUserDao;
import com.xxy.model.RootUser;
import com.xxy.service.RootUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("rootUserService")
public class RootUserServiceImpl implements RootUserService{

    @Resource
    RootUserDao rootUserDao;
    @Override
    public RootUser getrootuser(String username) {
        return rootUserDao.getrootuser(username);
    }
}
