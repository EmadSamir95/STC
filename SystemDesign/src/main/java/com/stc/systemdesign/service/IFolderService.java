package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.FolderDTO;
import org.springframework.security.core.Authentication;

public interface IFolderService {
    void create(FolderDTO dto, Authentication authentication);
}
