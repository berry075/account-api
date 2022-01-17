package com.berry.account.mock;

import com.berry.account.dao.User;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.stereotype.Component;

@Component
public class MockCustomUserSecurityContextFactory implements
    WithSecurityContextFactory<MockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(MockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String auth : customUser.authorities()) {
            authorities.add(new SimpleGrantedAuthority(auth));
        }

        User user = new User(0L, customUser.email(), customUser.tel(), null, null, null, null, null, false, authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, "password", user.getAuthorities());

        context.setAuthentication(authentication);

        return context;
    }
}
