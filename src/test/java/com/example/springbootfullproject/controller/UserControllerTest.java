package com.example.springbootfullproject.controller;

import com.example.springbootfullproject.dto.RegistrationRequest;
import com.example.springbootfullproject.service.UserService;
import com.example.springbootfullproject.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // add the exception's message
    @Test
    public void givenNullFirstName_whenRegisterUser_thenThrowMethodArgumentNotValidException() throws Exception {

        RegistrationRequest request = RegistrationRequest.builder()
                .firstName("")
                .lastName("Zouhair")
                .email("soufianezouhaironline@gmail.com")
                .phoneNumber("+212600000000")
                .password("Pass@123")
                .role("TEACHER")
                .build();

        mockMvc.perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.serializeToJson(request))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(result -> {

                                    assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException);

                                    MethodArgumentNotValidException ex = (MethodArgumentNotValidException) result.getResolvedException();
                                    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

                                    assertThat(fieldErrors, hasSize(greaterThanOrEqualTo(1)));
                                    // Assert the specific field error(s) and their messages
                                    for (FieldError fieldError : fieldErrors) {
                                        if ("firstName".equals(fieldError.getField())) {
                                            assertEquals("First name is required.", fieldError.getDefaultMessage());
                                        }
                                    }
                                });
    }
}