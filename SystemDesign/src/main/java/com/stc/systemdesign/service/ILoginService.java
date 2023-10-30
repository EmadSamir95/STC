package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.LoginDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface ILoginService {

    void login(LoginDTO dto, HttpServletResponse response);
}
