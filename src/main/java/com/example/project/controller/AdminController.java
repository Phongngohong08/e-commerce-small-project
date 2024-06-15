package com.example.project.controller;

import com.example.project.appuser.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AppUserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin:read')")
    public Object getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public String delete(@PathVariable("userId") Integer userId) {
        return userService.deleteUser(userId);
    }

    @DeleteMapping("/deleteUsers")
    @PreAuthorize("hasAuthority('admin:delete')")
    public String deleteAll() {
        return userService.deleteAllUsers();
    }
}
