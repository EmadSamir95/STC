package com.stc.systemdesign.dto;

import com.stc.systemdesign.validators.ITypeValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FileDTO {

    @ITypeValidator
    private String type;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull(message = "folder name cannot be null")
    @NotBlank(message = "folder name cannot be empty")
    private String folderName;

}
