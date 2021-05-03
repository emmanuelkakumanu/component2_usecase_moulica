package com.tweet.app.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AppUser implements UserDetails {


    private static final long serialVersionUID = 1L;
    private User user; // entity reference
    private Collection<? extends GrantedAuthority> authorities; // to store role details



    public AppUser(User user) {
        super();
        this.user = user;
        this.authorities = null;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }



    @Override
    public String getPassword() {
        return user.getPassWord();
    }



    @Override
    public String getUsername() {
        return user.getLoginId();
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
