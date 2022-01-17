package com.berry.account.user;

public interface UserService {

    User create(User user);

    User find(String id);

    int modifyPasswordById(String id, String password, String newPassword);
}
