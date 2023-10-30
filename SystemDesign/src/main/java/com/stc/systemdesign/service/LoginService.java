package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.LoginDTO;
import com.stc.systemdesign.security.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements ILoginService{

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Override
    public void login(LoginDTO dto, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJWT(authentication);
        setResponseHTTPOnly(token, response);
    }

    private void setResponseHTTPOnly(String jwt, HttpServletResponse response){
        Cookie cookie = new Cookie("JWT-Token", jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
