package com.example.project.controller;

import com.example.project.appuser.AppUser;
import com.example.project.appuser.AppUserService;
import com.example.project.appuser.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/appUser")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PatchMapping("/changePassword")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        appUserService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
