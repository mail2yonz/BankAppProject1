package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository=userRepository;
    }


    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public Long countByEmail(String email)
    {
        return userRepository.countByEmail(email);
    }

    public void saveAccountholder(User user)
    {
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveManager(User user)
    {
        user.setRoles(Arrays.asList(roleRepository.findByRole("MANAGER")));
        user.setEnabled(true);
        userRepository.save(user);

    }


}
