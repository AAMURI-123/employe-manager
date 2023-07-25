package com.umerscode.employeemanager.Service;

import com.umerscode.employeemanager.Entity.Users;
import com.umerscode.employeemanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    //private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository) {
        //this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Users registerNewUser(Users users){
        if(users.getUsername()== null || users.getUsername().equals("")
            || users.getPassword() == null || users.getPassword().equals(""))
            throw new IllegalStateException("Username or password is required");

        // encode the password before saving it
//        passwordEncoder.encode(users.getPassword());

     return    userRepository.save(users);

    }

}
