package com.example.samere.godknows.godknows.util;

import org.apache.commons.validator.routines.EmailValidator;

public class AccountEmailValidator extends EmailValidator {
    public AccountEmailValidator(boolean allowLocal) {
        super(allowLocal);
    }
}
