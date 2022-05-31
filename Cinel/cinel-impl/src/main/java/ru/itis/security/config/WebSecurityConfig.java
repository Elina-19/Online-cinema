package ru.itis.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.security.filter.TokenAuthorizationFilter;
import ru.itis.security.filter.TokenLogoutFilter;
import ru.itis.security.userdetails.TokenAuthenticationUserDetailsService;
import ru.itis.service.BlackListService;
import ru.itis.service.JwtTokenService;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationUserDetailsService authorizationUserDetailsService;
    private final JwtTokenService jwtTokenService;
    private final BlackListService blackListService;

    private static final String[] PERMIT_ALL = {
            "/websocket",
            "/api/v1/login",
            "/api/v1/token/user-info",
            "/api/v1/token/refresh"
    };

    private static final String[] IGNORE = {
            "/account-swagger/api-docs",
            "/swagger-ui.html"
    };

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(IGNORE);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilterAfter(tokenAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(logoutFilter(), LogoutFilter.class)
                .authorizeRequests()
                    .antMatchers(PERMIT_ALL).permitAll()
                    .and()
                .cors()
                    .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable();
    }

    public OncePerRequestFilter tokenAuthorizationFilter() {
        return new TokenAuthorizationFilter(jwtTokenService, authorizationUserDetailsService);
    }

    public TokenLogoutFilter logoutFilter(){
        return new TokenLogoutFilter(blackListService);
    }
}

