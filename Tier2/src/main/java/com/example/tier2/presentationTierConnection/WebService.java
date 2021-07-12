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
    public void register(@RequestParam String username, @RequestParam String password) { dataTierConnection.register(username,password); }
    @PutMapping("/user")
    public void updateUser(@RequestParam int id, @RequestParam String username, @RequestParam String password) { dataTierConnection.updateUser(id, username,password); }
}
