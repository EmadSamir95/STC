package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.FileDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    void create(FileDTO dto, MultipartFile file, Authentication authentication) throws IOException;
}
