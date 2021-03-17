//package com.decade.electricityprediction.validation;
//
//import com.baeldung.web.dto.UserDto;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class PasswordMatchesValidator implements ConstraintValidator<com.baeldung.validation.PasswordMatches, Object> {
//
//    @Override
//    public void initialize(final com.baeldung.validation.PasswordMatches constraintAnnotation) {
//        //
//    }
//
//    @Override
//    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
//        final UserDto user = (UserDto) obj;
//        return user.getPassword().equals(user.getMatchingPassword());
//    }
//
//}
