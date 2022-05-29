package ru.itis.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.MultiValueMap;
import ru.itis.exception.AuthenticationHeaderException;
import ru.itis.exception.IrrelevantTokenException;
import ru.itis.security.utils.AuthorizationHeaderHelper;
import ru.itis.service.JwtTokenService;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RequiredArgsConstructor
public class AuthorizationInterceptor implements ChannelInterceptor {

    private final UserDetailsService accountUserDetailsService;

    private final JwtTokenService jwtTokenService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        MessageHeaders headers = accessor.getMessageHeaders();

        if(StompCommand.CONNECT.equals(accessor.getCommand())) {
            MultiValueMap<String, String> nativeHeaders =
                    headers.get(StompHeaderAccessor.NATIVE_HEADERS, MultiValueMap.class);
            List<String> authHeaders = nativeHeaders.get(AUTHORIZATION);

            if (authHeaders != null) {
                String token = AuthorizationHeaderHelper.getTokenFromValidatedAuthorizationHeader(
                        authHeaders.get(0));

                if (Objects.nonNull(token)) {

                    if (!jwtTokenService.validateAccessToken(token)) {
                        throw new IrrelevantTokenException(token, "Token is irrelevant");
                    }

                    UserDetails userDetails = accountUserDetailsService.loadUserByUsername(token);
                    PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(
                            userDetails, token, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    accessor.setUser(authenticationToken);
                }
            } else {
                throw new AuthenticationHeaderException("Header for websocket connection is missing");
            }
        }
        return message;
    }
}
