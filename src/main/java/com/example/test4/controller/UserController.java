package com.example.test4.controller;

import com.example.test4.controller.userdto.UserDto;
import com.example.test4.entity.User;
import com.example.test4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    private void create(@RequestBody UserDto requestCreated){userService.create(requestCreated);}

    @PutMapping("{id}")
    private void update(@PathVariable Long id,@RequestBody UserDto requestUpdated){userService.update(id,requestUpdated);}

    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id){userService.delete(id);}

    @GetMapping("{id}")
    private User readOne(@PathVariable Long id){
        return userService
                .readOne(id)
                .orElse(null);
    }

    @GetMapping
    private List<User> readAll(){
        return userService.readAll();
    }



}
