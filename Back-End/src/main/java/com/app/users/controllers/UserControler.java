package com.app.users.controllers;


import com.app.users.dto.UserDTO;
import com.app.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserControler {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<UserDTO> getUsers()
    {
        return service.findAll();
    }

    @PostMapping("/user")
    public UserDTO creteUser(@RequestBody UserDTO user)
    {
        return service.add(user);
    }

    @PutMapping("/user/{id}")
    public UserDTO updateUser(@PathVariable(name="id") int id, @RequestBody UserDTO user) {
        return  service.update(user, id);
    }

    @DeleteMapping("/user/{id}")
    public UserDTO deleteUser(@PathVariable int id) {
        return service.remove(id);
    }


}
