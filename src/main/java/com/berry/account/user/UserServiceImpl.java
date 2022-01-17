package com.berry.account.user;

import com.berry.account.util.SignIdValidator;
import com.berry.account.util.SignIdValidator.SignIdType;
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
        SignIdType signIdType = SignIdValidator.getType(id);
        switch (signIdType) {
            case TEL:
                return userRepository.findByTel(id).orElseThrow();
            case EMAIL:
                return userRepository.findByEmail(id).orElseThrow();
            default:
                return userRepository.findById(Long.parseLong(id)).orElseThrow();
        }
    }

    @Override
    public void modifyPasswordById(String id, String password, String newPassword) {
        Long userId = Long.parseLong(id);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("not found user"));
        String storedPassword = user.getPassword();
        if (passwordEncoder.matches(password, storedPassword)) {
            userRepository.modifyPassword(userId ,storedPassword, passwordEncoder.encode(newPassword));
        }
    }
}
