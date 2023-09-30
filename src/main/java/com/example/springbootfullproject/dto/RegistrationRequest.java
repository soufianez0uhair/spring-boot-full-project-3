package com.example.springbootfullproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegistrationRequest {

    @NotEmpty(message = "First name is required.")
    private final String firstName;
    @NotEmpty(message = "Last name is required.")
    private final String lastName;
    @NotEmpty(message = "Email is required.")
    private final String email;
    @NotEmpty(message = "Phone number is required.")
    private final String phoneNumber;
    @NotEmpty(message = "Password is required.")
    private final String password;
    @NotEmpty(message = "Role is required.")
    private final String role;

}
