package com.example.tier2.presentationTierConnection;

import com.example.tier2.dataTierConnection.DataTierConnection;
import domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebService {
    private DataTierConnection dataTierConnection;

    public WebService() {
        this.dataTierConnection = new DataTierConnection();
    }

    @GetMapping("/user")
    public User login(@RequestParam String username, @RequestParam String password) { return dataTierConnection.login(username,password); }
    @PostMapping("/user")
    public boolean register(@RequestParam String username, @RequestParam String password) { return dataTierConnection.register(username,password); }
    @PutMapping("/user")
    public boolean updateUser(@RequestParam int id, @RequestParam String username, @RequestParam String password) { return dataTierConnection.updateUser(id, username,password); }
}
