package com.example.jwtAuth.models.userDetailsImpl;

import com.example.jwtAuth.models.entities.Role;
import com.example.jwtAuth.models.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(Long id, String login, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );
    }
    public static Collection<? extends GrantedAuthority> getAuthorities(Role role){
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
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
