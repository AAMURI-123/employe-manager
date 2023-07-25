package com.umerscode.employeemanager.Controller;

import com.umerscode.employeemanager.Entity.Users;
import com.umerscode.employeemanager.Repository.UserRepository;
import com.umerscode.employeemanager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("signup")
    public ResponseEntity<?> registerNewUser(@RequestBody Users users){
        Users user = new Users(users.getUsername(),users.getPassword(),users.getRole(),
                true, true,true,true);

        return new ResponseEntity<>(userService.registerNewUser(user), CREATED);
    }

}
