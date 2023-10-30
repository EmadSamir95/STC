package com.stc.systemdesign.dto;

import com.stc.systemdesign.validators.ITypeValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FolderDTO {

    @ITypeValidator(message = "provided type is invalid")
    private String type;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull(message = "space name cannot be null")
    @NotBlank(message = "space name cannot be empty")
    private String spaceName;
}
