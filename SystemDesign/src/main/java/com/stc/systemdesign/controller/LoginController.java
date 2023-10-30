package com.stc.systemdesign.controller;

import com.stc.systemdesign.dto.LoginDTO;
import com.stc.systemdesign.service.ILoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final ILoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto, HttpServletResponse response){
        loginService.login(dto, response);
        return new ResponseEntity<>("logged in successfully", HttpStatus.OK);
    }

}
