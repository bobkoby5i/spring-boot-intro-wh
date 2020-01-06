package com.koby5i.wh.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class MyUserDetails implements UserDetails {

    private String userName;


    //use=user
    //password=secret123
    //encodedPassword='$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2'
    //password='password
    //encodedPassword='$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC'

    public MyUserDetails(String userName){
        this.userName = userName;
    }

    public MyUserDetails(){

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return "$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC";
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
