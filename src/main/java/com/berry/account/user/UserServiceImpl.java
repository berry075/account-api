package com.berry.account.user;

import com.berry.account.util.SignIdValidator;
import com.berry.account.util.SignIdValidator.SignIdType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
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
    public int modifyPasswordById(String id, String password, String newPassword) {
        return userRepository.modifyPassword(Long.parseLong(id), password, newPassword);
    }
}
