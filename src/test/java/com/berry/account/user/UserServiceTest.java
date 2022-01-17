package com.berry.account.user;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SecurityUserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup() {
        user = User.builder()
            .tel("01012345678")
            .email("first@email.com")
            .name("첫번째")
            .nickname("nickuser1")
            .password("pass0001")
            .build();
    }


    @Test
    void 신규사용자_계정_생성() {
        // given
        User inputUser = User.builder()
            .tel("01012345678")
            .email("first@email.com")
            .name("첫번째")
            .nickname("nickuser1")
            .password("pass0001")
            .build();

        given(userService.create(inputUser)).willReturn(user);

        // when
        userRepository.save(inputUser);

        // then
        verify(userRepository).save(inputUser);
    }

    @Test
    void find() {

    }

    @Test
    void modifyPassword() {
    }
}