package com.example.tier2.service;

import com.example.tier2.dataTierConnection.UserDataTierConnection;
import com.example.tier2.service.dto.RegisterUserDTO;
import com.example.tier2.service.dto.UpdateUserDTO;
import com.example.tier3.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDataTierConnection connection;

    public User login(String username, String password) {
        return connection.findUserByUsernameAndPassword(username, password);
    }

    public User register(RegisterUserDTO dto) {
        return connection.createUser(dto.getUsername(), dto.getPassword());
    }

    public User update(int id, UpdateUserDTO dto) {
        return connection.updateUser(id, dto.getUsername(), dto.getPassword());
    }

    public boolean deleteAccount(int id) {
        return connection.deleteUser(id);
    }

}
