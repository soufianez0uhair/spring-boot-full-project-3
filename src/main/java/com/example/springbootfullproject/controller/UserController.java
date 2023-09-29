package com.example.springbootfullproject;

import com.example.springbootfullproject.dto.RegistrationRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping("/register")
    private void registerUser(RegistrationRequest request) {
        if(request.getFirstName() == null || request.getFirstName() == "") {
            throw new RuntimeException("First name is required.");
        }
    }
}
