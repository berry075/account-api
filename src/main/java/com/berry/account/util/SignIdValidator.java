package com.berry.account.util;

public class SignIdValidator {

    public enum SignIdType {
        NONE, TEL, EMAIL
    }

    public static SignIdType getType(String signId) {
        if (signId.matches("[0-9]{2,}")) {
            return SignIdType.TEL;
        }

        if (signId.contains("@")) {
            return SignIdType.EMAIL;
        }

        return SignIdType.NONE;
    }
}
