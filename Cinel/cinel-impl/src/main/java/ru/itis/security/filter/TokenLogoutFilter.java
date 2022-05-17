package ru.itis.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.security.utils.AuthorizationHeaderHelper;
import ru.itis.service.BlackListService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class TokenLogoutFilter extends OncePerRequestFilter {

    private final BlackListService blackListService;

    private final static RequestMatcher logoutRequest = new AntPathRequestMatcher("/api/v1/logout", "GET");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (logoutRequest.matches(request)) {
            String token = AuthorizationHeaderHelper.getTokenFromValidatedAuthorizationHeader(
                    request.getHeader(HttpHeaders.AUTHORIZATION));

            if (token != null) {
                blackListService.save(token);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
