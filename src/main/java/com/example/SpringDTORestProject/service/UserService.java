package com.example.SpringDTORestProject.service;

import com.example.SpringDTORestProject.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsersListFromDatabase();
    public void saveUser(User user);
    public User findById(Integer id);
    public void delete(User user);
    public User findByMobile(String mobile_no);
}
