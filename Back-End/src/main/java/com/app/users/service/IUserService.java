package com.app.users.service;

import com.app.users.dto.userDTO;

import java.util.List;

public interface IUserService {


    public userDTO add(userDTO user);
    public userDTO remove(int id);
    public userDTO update(userDTO user, int id);
    public List<userDTO> findAll();
    public userDTO findUser(int id);
}
