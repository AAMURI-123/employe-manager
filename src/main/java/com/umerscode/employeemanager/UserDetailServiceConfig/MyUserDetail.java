package com.umerscode.employeemanager.UserDetailServiceConfig;

import com.umerscode.employeemanager.Entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyUserDetail implements UserDetails {

   private String username;
   private String password;
   private Boolean isAccountNonExpired;
   private Boolean isAccountNonLocked;
   private Boolean isCredentialsNonExpired;
   private Boolean isEnabled;
   private String role;

    public MyUserDetail(Users users) {
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.isAccountNonExpired = users.getIsAccountNonExpired();
        this.isAccountNonLocked = users.getIsAccountNonLocked();
        this.isCredentialsNonExpired = users.getIsCredentialsNonExpired();
        this.isEnabled = users.getIsEnabled();
        this.role = users.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
