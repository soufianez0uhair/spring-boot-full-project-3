package com.example.springbootfullproject.controller;

import com.example.springbootfullproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // add the exception's message
    @Test
    public void givenNullFirstName_whenRegisterUser_thenThrowMethodArgumentNotValidException() throws Exception {
        RegistrationRequest request = new RegistrationRequest("", "Zouhair", "soufianezouhaironline@gmail.com", "0614671572", "Pass@123", "STUDENT");
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), request.getPassword());
        user.addRole(new Role("STUDENT"));
        given(UserService.registerUser(request)).willReturn(new RegistrationResponse("dcjndjcndjcndj", roles));

        mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON))
                .content(JsonConverter.convertToJson(request))
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }
    }
}