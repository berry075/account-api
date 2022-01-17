package com.berry.account.sign;

import com.berry.account.user.User;
import com.berry.account.user.UserRepository;
import com.berry.account.util.SignIdValidator;
import com.berry.account.util.SignIdValidator.SignIdType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInOutServiceImpl implements SignInOutService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signInBySignId(String signId, String password) {
        SignIdType signIdType = SignIdValidator.getType(signId);

        User user = null;
        switch (signIdType) {
            case TEL:
                user = userRepository.findByTel(signId).get();
                break;
            case EMAIL:
                user = userRepository.findByEmail(signId).get();
                break;
            default:
        }

        /* TODO: 비밀번호 비교하기 */
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("not found user");
        }

        return user;
    }
}
