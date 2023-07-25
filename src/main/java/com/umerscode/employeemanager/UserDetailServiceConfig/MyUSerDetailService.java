package com.umerscode.employeemanager.UserDetailServiceConfig;

import com.umerscode.employeemanager.Entity.Users;
import com.umerscode.employeemanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUSerDetailService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public MyUSerDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username).get();
        if(user.getUsername() == null || user.getUsername().equals(""))
            throw new UsernameNotFoundException("User with a name " + username+ " not found");
        System.out.println(user);
        return new MyUserDetail(user);
    }
}
