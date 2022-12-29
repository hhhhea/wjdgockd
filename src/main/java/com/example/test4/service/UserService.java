package com.example.test4.service;

import com.example.test4.controller.userdto.UserDto;
import com.example.test4.entity.User;
import com.example.test4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(UserDto user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("Email already exists");
        if (!user.getPassword().equals(user.getPasswordConfirm()))
            throw new IllegalArgumentException("password and password confirm not same");
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
    }

    public void update(Long id, UserDto user) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("email not exists");
        }
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            User updateduser = foundUser.get();
            updateduser.setName(user.getName());
            userRepository.save(updateduser);
        }
        else{
            throw new NoSuchElementException("email not exists");
        }

    }

    public void delete(Long id){
        Optional<User> aUser = readOne(id);
        if(aUser.isPresent())
            userRepository.delete(aUser.get());
    }

    public Optional<User> readOne(Long id){
        return userRepository.findById(id);
    }

    public List<User> readAll(){
        return userRepository.findAll();
    }
}
