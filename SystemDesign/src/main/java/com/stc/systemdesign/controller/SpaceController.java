package com.stc.systemdesign.controller;

import com.stc.systemdesign.dto.SpaceDTO;
import com.stc.systemdesign.service.ISpaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/space")
@RequiredArgsConstructor
public class SpaceController {

    private final ISpaceService spaceService;

    @PostMapping
    public ResponseEntity<?> addSpace(@RequestBody @Valid SpaceDTO dto, Authentication authentication) {
        spaceService.create(dto, authentication);
        return new ResponseEntity<>("space created successfully", HttpStatus.CREATED);
    }

}
