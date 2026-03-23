package com.app.users.service;

import com.app.users.dto.UserDTO;

import java.util.List;

public interface IUserService {


    public UserDTO add(UserDTO user);
    public UserDTO remove(int id);
    public UserDTO update(UserDTO user, int id);
    public List<UserDTO> findAll();
    public UserDTO findUser(int id);
}
