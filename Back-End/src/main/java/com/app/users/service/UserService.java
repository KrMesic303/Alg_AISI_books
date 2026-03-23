package com.app.users.service;

import com.app.users.models.User;
import com.app.users.dao.UserDAO;
import com.app.users.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserDAO dao;

    @Override
    public UserDTO add(UserDTO user) {
        User entity = toUser(user);
        return fromUser(dao.save(entity));
    }

    @Override
    public UserDTO remove(int id) {
            Optional<User> user = dao.findById(id);

            if(user.isPresent()) {
                dao.deleteById(id);
                return fromUser(user.get());
            }

            throw new RuntimeException("User not found");
    }

    @Override
    public UserDTO update(UserDTO newUserDTO, int id) {
        Optional<User> userOld = dao.findById(id);
        if(userOld.isPresent()){
            User existing = userOld.get();
            existing.setName(newUserDTO.getName());
            existing.setLastName(newUserDTO.getLastName());
            return fromUser(dao.save(existing));
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public List<UserDTO> findAll() {
        return dao.findAll().stream()
                .map(this::fromUser)
                .toList();
    }

    @Override
    public UserDTO findUser(int id) {
        Optional<User> user = dao.findById(id);
        if (user.isPresent()) {
            return fromUser(user.get());
        }
        return null;
    }

    public User toUser(UserDTO userDTO) {
        User.UserBuilder builder = User.builder()
                .name(userDTO.getName())
                .lastName(userDTO.getLastName());

        if (userDTO.getId() != null) {
            builder.id(userDTO.getId());
        }

        return builder.build();
    }

    public UserDTO fromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .lastName(user.getLastName())
                .name(user.getName())
                .build();
    }
}
