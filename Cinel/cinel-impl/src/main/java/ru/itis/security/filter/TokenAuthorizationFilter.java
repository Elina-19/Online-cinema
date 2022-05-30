package ru.itis.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.exception.token.IrrelevantTokenException;
import ru.itis.security.utils.AuthorizationHeaderHelper;
import ru.itis.service.JwtTokenService;
import ru.itis.utils.HttpResponseUtil;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService accountUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException {
        try {
            String token = AuthorizationHeaderHelper.getTokenFromValidatedAuthorizationHeader(
                    request.getHeader(AUTHORIZATION));
            log.info("Loading user for Authorization token: {}", token);

            if (Objects.nonNull(token)) {

                if (!jwtTokenService.validateAccessToken(token)){
                    throw new IrrelevantTokenException(token, "Token is irrelevant");
                }

                PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(
                        accountUserDetailsService.loadUserByUsername(token), token);

                if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else if (!SecurityContextHolder.getContext().getAuthentication().getCredentials().equals(token)) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            chain.doFilter(request, response);
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            HttpResponseUtil.putExceptionInResponse(request, response,
                    exception, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

