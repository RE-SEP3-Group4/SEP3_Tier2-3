package com.example.tier2.presentationTierConnection;

import com.example.tier2.dataTierConnection.UserDataTierConnection;
import com.example.tier2.service.UserService;
import com.example.tier2.service.dto.RegisterUserDTO;
import com.example.tier2.service.dto.UpdateUserDTO;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users/")
    public User login(@RequestParam String username, @RequestParam String password) {
        return service.login(username, password);
    }

    @PostMapping("/users/register")
    public User register(@RequestBody RegisterUserDTO dto) {
        return service.register(dto);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody UpdateUserDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable("id") int id) {
        return service.deleteAccount(id);
    }
}
