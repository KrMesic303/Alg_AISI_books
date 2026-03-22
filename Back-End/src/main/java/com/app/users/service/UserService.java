package com.app.users.service;

import com.app.users.models.User;
import com.app.users.dao.UserDAO;
import com.app.users.dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserDAO dao;

    @Override
    public userDTO add(userDTO user) {
        User entity = toUser(user);
        return fromUser(dao.save(entity));
    }

    @Override
    public userDTO remove(int id) {
            Optional<User> user = dao.findById(id);

            if(user.isPresent()) {
                dao.deleteById(id);
                return fromUser(user.get());
            }

            throw new RuntimeException("User not found");
    }

    @Override
    public userDTO update(userDTO newUserDTO, int id) {
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
    public List<userDTO> findAll() {
        return dao.findAll().stream()
                .map(this::fromUser)
                .toList();
    }

    @Override
    public userDTO findUser(int id) {
        Optional<User> user = dao.findById(id);
        if (user.isPresent()) {
            return fromUser(user.get());
        }
        return null;
    }

    public User toUser(userDTO userDTO) {
        User.UserBuilder builder = User.builder()
                .name(userDTO.getName())
                .lastName(userDTO.getLastName());

        if (userDTO.getId() != null) {
            builder.id(userDTO.getId());
        }

        return builder.build();
    }

    public userDTO fromUser(User user) {
        return userDTO.builder()
                .id(user.getId())
                .lastName(user.getLastName())
                .name(user.getName())
                .build();
    }
}
