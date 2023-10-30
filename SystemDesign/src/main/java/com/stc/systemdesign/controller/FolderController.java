package com.stc.systemdesign.controller;

import com.stc.systemdesign.dto.FolderDTO;
import com.stc.systemdesign.service.IFolderService;
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
@RequestMapping("/folder")
@RequiredArgsConstructor
public class FolderController {

    private final IFolderService folderService;

    @PostMapping
    public ResponseEntity<?> createFolder(@RequestBody @Valid FolderDTO dto, Authentication authentication){
        folderService.create(dto, authentication);
        return new ResponseEntity<>("folder is created successfully", HttpStatus.CREATED);
    }
}
