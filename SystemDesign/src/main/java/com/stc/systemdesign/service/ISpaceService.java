package com.stc.systemdesign.service;

import com.stc.systemdesign.dto.SpaceDTO;
import org.springframework.http.ResponseEntity;

public interface ISpaceService {
    void create(SpaceDTO dto);
}
