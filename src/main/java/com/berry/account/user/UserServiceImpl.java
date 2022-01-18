package com.berry.account.user;

import com.berry.account.exception.ErrorCode;
import com.berry.account.exception.ErrorCodeException;
import com.berry.account.util.SignIdValidator;
import com.berry.account.util.SignIdValidator.SignIdType;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    @Override
    public User find(String id) {
        try {
            id = URLDecoder.decode(id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ErrorCodeException(ErrorCode.REQUEST_INVALID_VALUE);
        }
        SignIdType signIdType = SignIdValidator.getType(id);
        switch (signIdType) {
            case TEL:
                return userRepository.findByTel(id)
                    .orElseThrow(() -> new ErrorCodeException(ErrorCode.USER_NOT_FOUND_BY_TEL));
            case EMAIL:
                return userRepository.findByEmail(id)
                    .orElseThrow(() -> new ErrorCodeException(ErrorCode.USER_NOT_FOUND_BY_EMAIL));
            default:
                return userRepository.findById(Long.parseLong(id))
                    .orElseThrow(() -> new ErrorCodeException(ErrorCode.USER_NOT_FOUND_BY_ID));
        }
    }

    @Override
    public void modifyPasswordById(ResetPassword resetPassword) {
        User user = find(resetPassword.getSignId());
        String storedPassword = user.getPassword();
        if (passwordEncoder.matches(resetPassword.getPassword(), storedPassword)) {
            userRepository.modifyPassword(user.getId(), storedPassword,
                passwordEncoder.encode(resetPassword.getNewPassword()));
        }
    }
}
