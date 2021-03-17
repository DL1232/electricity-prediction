//package com.decade.electricityprediction.service.impl;
//
//import com.decade.electricityprediction.web.dto.UserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl {
//    @Autowired
//    private UserRepository repository;
//
//    @Transactional
//    @Override
//    public User registerNewUserAccount(UserDto userDto)
//            throws UserAlreadyExistException {
//
//        if (emailExist(userDto.getEmail())) {
//            throw new UserAlreadyExistException(
//                    "There is an account with that email address: "
//                            +  userDto.getEmail());
//        }
//        ...
//        // the rest of the registration operation
//    }
//    private boolean emailExist(String email) {
//        return userRepository.findByEmail(email) != null;
//    }
//}
