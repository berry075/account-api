package com.berry.account.user;

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
        if (id.matches("0[0-9]*")) {
            return userRepository.findByTel(id).orElseThrow();
        }

        if (id.matches(".*\\@.*\\..*")) {
            return userRepository.findByEmail(id).orElseThrow();
        }

        return userRepository.findById(Long.parseLong(id)).orElseThrow();
    }

    @Override
    public int modifyPasswordById(String id, String password, String newPassword) {
        return userRepository.modifyPassword(Long.parseLong(id), password, newPassword);
    }
}
