package com.berry.account.sign;

import com.berry.account.user.User;

public interface SignInOutService {
    User signInBySignId(String signId, String password);
}
