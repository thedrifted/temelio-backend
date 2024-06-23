package com.temelio_demo.demo.Utils;

import org.apache.commons.lang3.StringUtils;

public class EmailValidatorUtils {

    private String EMAIL_REGEX="[a-zA-Z0-9.!#$%&*+=?^_~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+";

    public static boolean isEmailValid(String email) {
        if(StringUtils.isBlank(email)){
            return false;
        }
        return true;
    }
}
