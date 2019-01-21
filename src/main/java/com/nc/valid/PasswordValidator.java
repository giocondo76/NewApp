package com.nc.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    public void initialize(ValidPassword constraint) {
    }

    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.equals(""))
            return false;
        Pattern pattern = Pattern.compile("(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}