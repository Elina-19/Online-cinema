package ru.itis.security.userdetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.itis.exception.token.AuthenticationHeaderException;
import ru.itis.model.Account;
import ru.itis.model.Role;
import ru.itis.service.JwtTokenService;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final JwtTokenService jwtTokenService;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        String strToken = (String) token.getCredentials();
        return loadUserDetails(jwtTokenService.getUserByToken(strToken), strToken);
    }

    private UserDetails loadUserDetails(Account account, String token) {
        try {
            return Optional.ofNullable(account)
                    .map(acc -> {
                        List<SimpleGrantedAuthority> authorities = getAuthorities(acc.getRole());
                        return AccountUserDetails.builder()
                                .id(acc.getId())
                                .username(acc.getUsername())
                                .createDate(acc.getCreateDate())
                                .authorities(authorities)
                                .isAccountNonExpired(true)
                                .isCredentialsNonExpired(true)
                                .isAccountNonLocked(true)
                                .isEnabled(acc.getConfirmed())
                                .token(token)
                                .build();
                    })
                    .orElseThrow(() -> new UsernameNotFoundException("Unknown user by token " + token));
        } catch (Exception exception) {
            throw new AuthenticationHeaderException(exception.getMessage());
        }
    }

    private List<SimpleGrantedAuthority> getAuthorities(Role role) {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role));
    }
}
