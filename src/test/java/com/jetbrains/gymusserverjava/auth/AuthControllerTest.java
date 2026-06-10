package com.jetbrains.gymusserverjava.auth;

import com.jetbrains.gymusserverjava.auth.dtos.requests.LoginRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockitoBean
    AuthService authService;

    @Test
    void shouldReturnOkWhenLoginCredentialsAreValid() {
        // given
        var loginRequest = new LoginRequestDto("benianus", "123456789");

        // when
        var response = testRestTemplate.postForEntity(
                "/api/auth/login",
                loginRequest,
                String.class
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}