package com.berry.account.user;

public interface UserService {

    User create(User user);

    User find(String id);

    void modifyPasswordById(ResetPassword resetPassword);
}
