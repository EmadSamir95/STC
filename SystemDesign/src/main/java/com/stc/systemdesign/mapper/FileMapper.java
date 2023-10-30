package com.stc.systemdesign.mapper;

import com.stc.systemdesign.dto.FileMetaRespDTO;
import com.stc.systemdesign.entity.FileEntity;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public FileMetaRespDTO fileToMetaDataResponse(FileEntity file){
        return FileMetaRespDTO.builder()
                .fileName(file.getItem().getName())
                .permissionGroup(file.getItem().getPermissionGroupEntity().getName())
                .build();
    }
}
