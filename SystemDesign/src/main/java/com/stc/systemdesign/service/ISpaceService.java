package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.SpaceDTO;
import org.springframework.security.core.Authentication;

public interface ISpaceService {
    void create(SpaceDTO dto, Authentication authentication);
}
