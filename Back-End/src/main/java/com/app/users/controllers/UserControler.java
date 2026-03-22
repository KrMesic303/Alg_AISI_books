package com.app.users.controllers;


import com.app.users.dto.userDTO;
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
    public List<userDTO> getUsers()
    {
        return service.findAll();
    }

    @PostMapping("/user")
    public userDTO creteUser(@RequestBody userDTO user)
    {
        return service.add(user);
    }

    @PutMapping("/user/{id}")
    public userDTO updateUser(@PathVariable(name="id") int id, @RequestBody userDTO user) {
        return  service.update(user, id);
    }

    @DeleteMapping("/user/{id}")
    public userDTO deleteUser(@PathVariable int id) {
        return service.remove(id);
    }


}
