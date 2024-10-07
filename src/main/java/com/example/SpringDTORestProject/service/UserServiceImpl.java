package com.example.SpringDTORestProject.service;

import com.example.SpringDTORestProject.entity.User;
import com.example.SpringDTORestProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsersListFromDatabase(){
        return userRepository.findAll();

    }
    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }
    @Override
    public User findById(Integer id){
        return userRepository.findById(id).get();

    }

    @Override
    public void delete(User user ){
        userRepository.delete(user);

    }

    @Override
    public User findByMobile(String mobile_no){
        return userRepository.findByMobile(mobile_no);
    }
}
