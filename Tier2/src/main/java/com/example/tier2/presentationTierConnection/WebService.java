package com.example.tier2.presentationTierConnection;

import com.example.tier2.dataTierConnection.DataTierConnection;
import domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebService {
    private DataTierConnection dataTierConnection;

    public WebService() {
        this.dataTierConnection = new DataTierConnection();
    }

    @GetMapping("/user")
    public User login(@RequestParam String username, @RequestParam String password) { return dataTierConnection.login(username,password); }
}
