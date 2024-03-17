package ru.readles.readlesshop.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.readles.readlesshop.entity.UsersEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private UsersEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(user.getRole().split(","))
        .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public MyUserDetails(UsersEntity user) {
        this.user = user;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getLogin();
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
