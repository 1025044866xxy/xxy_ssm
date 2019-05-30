package com.xxy.service.Impl;


import com.xxy.dao.UserDao;
import com.xxy.model.User;
import com.xxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public  class UserServiceImpl implements UserService {
    @Resource
    private UserDao userdao;

    @Override
    public User getuser(String username) {
        return userdao.getuser(username);
    }

    public void insertuser(User user) {
        userdao.insertuser(user);
    }

    @Override
    public List<User> getalluser() {
        return userdao.getalluser();
    }

    @Override
    public Integer getcount() {
        return userdao.getcount();
    }

    @Override
    public int deleteUser(Integer id) {
        return userdao.deleteUser(id);
    }

}
