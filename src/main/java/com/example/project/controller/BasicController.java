package com.example.project.controller;

import com.example.project.appuser.AppUser;
import com.example.project.appuser.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/basic")
public class BasicController {

    private static final List<AppUser> users = List.of(
            new AppUser("John Doe", "john@gmail", "password", Role.USER),
            new AppUser("Jane Doe", "jane@gmail", "password", Role.ADMIN)
    );

    @GetMapping
    public String getBasic() {
        return "this is a basic controller";
    }

    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return users;
    }
}
