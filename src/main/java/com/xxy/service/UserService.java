package com.xxy.service;

import com.xxy.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public User getuser(String username);
    public void insertuser(User user);
    public List<User> getalluser();
    public Integer getcount();
    public int deleteUser(@Param("userid") Integer id);
}
