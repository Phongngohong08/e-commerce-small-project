package com.example.project.dtos;

import java.util.List;

public record AppUserDTO(
        Integer id,
        String fullName,
        String email,
        String role,
        List<String> authorities) {
}
