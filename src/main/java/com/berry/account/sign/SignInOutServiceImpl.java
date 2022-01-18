package com.berry.account.sign;

import com.berry.account.exception.ErrorCode;
import com.berry.account.exception.ErrorCodeException;
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
    public User signInBySignId(SignInUser signInUser) {
        String signId = signInUser.getSignId();
        SignIdType signIdType = SignIdValidator.getType(signId);

        User user = null;
        switch (signIdType) {
            case TEL:
                user = userRepository.findByTel(signId).orElseThrow(() -> new ErrorCodeException(
                    ErrorCode.USER_NOT_FOUND_BY_TEL));
                break;
            case EMAIL:
                user = userRepository.findByEmail(signId)
                    .orElseThrow(() -> new ErrorCodeException(ErrorCode.USER_NOT_FOUND_BY_EMAIL));
                break;
            default:
        }

        if (!passwordEncoder.matches(signInUser.getPassword(), user.getPassword())) {
            throw new ErrorCodeException(ErrorCode.USER_INVALID_PASSWORD);
        }

        return user;
    }

    @Override
    public User signOutBySignId(SignInUser signInUser) {
        return null;
    }
}
