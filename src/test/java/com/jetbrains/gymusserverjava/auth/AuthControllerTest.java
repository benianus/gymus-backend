package com.jetbrains.gymusserverjava.auth;

import com.jetbrains.gymusserverjava.auth.dtos.requests.LoginRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @MockitoBean
    AuthService authService;

    @Autowired
    MockMvc mock;

    @Test
    void shouldReturnOkWhenLoginCredentialsAreValid() throws Exception {
        // given
        var loginRequest = new LoginRequestDto("benianus", "123456789");
        
        // when

        when(authService.login(any())).thenReturn(any());

        // then
        mock.perform(post("/api/auth/login")
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(loginRequest.toString()))
            .andExpect(status().isOk());

    }

}