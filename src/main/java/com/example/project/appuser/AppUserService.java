package com.example.project.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (AppUser) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }
        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        // save the new password
        appUserRepository.save(user);
    }

    public String deleteUser(Integer userId) {
        boolean exists = appUserRepository.existsById(userId);
        if(!exists){throw new IllegalStateException("User with id " + userId + " does not exist");}
        appUserRepository.deleteById(userId);
        return "deleted " + userId;
    }

    public String deleteAllUsers() {
        appUserRepository.deleteAll();
        return "All users deleted";
    }

    public List<String> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        return users.stream().map(AppUser::getEmail).toList();
    }
}
