package com.berry.account.user;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.berry.account.mock.MockCustomUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration
class UserControllerTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    @MockCustomUser(authorities = "EDIT_PROFILE")
    public void SMS_인증_사용자_회원가입하기() throws Exception {
        mockMvc.perform(post("/users"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    @MockCustomUser()
    public void SMS_미인증_사용자_회원가입하기() throws Exception {
        mockMvc.perform(post("/users"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    @MockCustomUser(role = "USER")
    public void 가입_사용자_내정보보기() throws Exception {
        mockMvc.perform(get("/users/1"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    @MockCustomUser()
    public void 미가입_사용자_내정보보기() throws Exception {
        mockMvc.perform(get("/users/1"))
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }
}