package com.jetbrains.gymusserverjava.auth;

import com.jetbrains.gymusserverjava.auth.dtos.requests.LoginRequestDto;
import com.jetbrains.gymusserverjava.auth.dtos.responses.AuthResponseDto;
import com.jetbrains.shared.security.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtHelper jwtHelper;

    @Autowired
    private MockMvc mock;

    @Test
    void shouldReturnOkWhenLoginCredentialsAreValid() throws Exception {
        // given
        var loginRequest = new LoginRequestDto("benianus", "123456789");
        var authResponse = new AuthResponseDto(
                "",
                "",
                "benianus",
                "OWNER",
                0
        );

        // when
        when(authService.login(loginRequest)).thenReturn(authResponse);

        // then
        mock.perform(post("/api/auth/login")
                             .with(user("benianus").password("123456789").roles("OWNER"))
                             .contentType(MediaType.APPLICATION_JSON)
                             .content(loginRequest.toString()))
            .andExpect(status().isOk());

    }

}