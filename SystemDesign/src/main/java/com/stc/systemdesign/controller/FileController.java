package com.stc.systemdesign.controller;

import com.stc.systemdesign.dto.FileDTO;
import com.stc.systemdesign.dto.FileMetaRespDTO;
import com.stc.systemdesign.service.IFileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final IFileService fileService;

    @PostMapping
    public ResponseEntity<?> createFile(@RequestBody @Valid FileDTO dto,
                                        @RequestParam("file") MultipartFile file,
                                        Authentication authentication
    ) throws IOException {
        fileService.create(dto, file, authentication);
        return new ResponseEntity<>("file is created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> download(@PathVariable("filename") String filename,
                                      Authentication authentication){
        byte[] data = fileService.download(filename, authentication);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<?> view(@PathVariable("filename") String filename, Authentication authentication){
        FileMetaRespDTO response = fileService.view(filename, authentication);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}