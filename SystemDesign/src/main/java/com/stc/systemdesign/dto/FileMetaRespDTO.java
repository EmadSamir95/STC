package com.stc.systemdesign.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FileMetaRespDTO {

    private String fileName;
    private String permissionGroup;

}
