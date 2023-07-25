package com.umerscode.employeemanager.securityConfig;

import com.umerscode.employeemanager.UserDetailServiceConfig.MyUSerDetailService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.*;
@Configuration
@EnableWebSecurity
@NoArgsConstructor
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


  private final MyUSerDetailService uSerDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
        // generate Jwt after success authentication

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
//                .antMatchers(HttpMethod.GET,"/employee/getAll").hasAnyRole("ADMIN","USER")
//                .antMatchers(HttpMethod.POST,"/employee/add").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/employee/update").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/employee/find/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/employee/*").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated().and().formLogin();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
