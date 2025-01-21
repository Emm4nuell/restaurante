package br.com.management_restaurant.service;

import br.com.management_restaurant.dto.request.UserAuthRequest;
import br.com.management_restaurant.dto.response.UserTokenResponse;
import br.com.management_restaurant.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserTokenResponse signin(UserAuthRequest userAuthRequest){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userAuthRequest.username(), userAuthRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return UserTokenResponse.builder()
                .token(jwtService.gerarToken(userAuthRequest.username()))
                .build();
    }
}
