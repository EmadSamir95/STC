package com.stc.systemdesign.controller;

import com.stc.systemdesign.dto.FileDTO;
import com.stc.systemdesign.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final IFileService fileService;

    @PostMapping
    public ResponseEntity<?> createFile(@RequestBody FileDTO dto, @RequestParam("file") MultipartFile file) throws IOException {
        fileService.create(dto, file);
        return new ResponseEntity<>("file is created successfully", HttpStatus.CREATED);
    }
}
