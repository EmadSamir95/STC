package com.stc.systemdesign.controller;

import com.stc.systemdesign.dto.SpaceDTO;
import com.stc.systemdesign.service.ISpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addSpace(@RequestBody SpaceDTO dto) {
        spaceService.create(dto);
        return new ResponseEntity<>("space created successfully", HttpStatus.CREATED);
    }

}
