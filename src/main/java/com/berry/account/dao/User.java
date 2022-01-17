package com.berry.account.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String tel;
    private String name;
    private String nickname;
    private String password;
    private Timestamp signUpAt;
    private Timestamp latestUpdateAt;

    private boolean locked;
    private Collection<GrantedAuthority> authorities;



    /**
     * security에서 사용되는 username
     */
    @Override
    public String getUsername() {
        return tel;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !locked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
