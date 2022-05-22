package ru.itis.security.userdetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.dto.enums.Role;
import ru.itis.dto.response.AccountResponse;
import ru.itis.exception.token.AuthenticationHeaderException;
import ru.itis.service.JwtTokenService;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenAuthenticationUserDetailsService implements UserDetailsService {

    private final JwtTokenService jwtTokenService;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        return loadUserDetails(jwtTokenService.getUserInfoByToken(token), token);
    }

    private UserDetails loadUserDetails(AccountResponse accountResponse, String token) {
        try {
            return Optional.ofNullable(accountResponse)
                    .map(account -> {
                        List<SimpleGrantedAuthority> authorities = getAuthorities(account.getRole());
                        return AccountUserDetails.builder()
                                .id(account.getId())
                                .username(account.getUsername())
                                .createDate(null)
                                .authorities(authorities)
                                .isAccountNonExpired(true)
                                .isCredentialsNonExpired(true)
                                .isAccountNonLocked(true)
                                .isEnabled(true)
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
