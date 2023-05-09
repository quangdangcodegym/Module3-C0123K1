package com.codegym.casetemplate.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String NAME_REGEX = "^[A-Za-z][A-Za-z0-9_ ]{7,19}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    private static final String DESCRIPTION_REGEX = "^[A-Za-z][A-Za-z0-9_ ]{14,49}$";

    public static boolean isNameValid(String name) {
        return Pattern.matches(NAME_REGEX, name);
    }
    public static boolean isDescriptionValid(String description) {
        return Pattern.matches(DESCRIPTION_REGEX, description);
    }
    public static boolean isEmailValid(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }


}
