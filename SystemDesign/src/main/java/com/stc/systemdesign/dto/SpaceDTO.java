package com.stc.systemdesign.dto;

import com.stc.systemdesign.entity.Type;
import com.stc.systemdesign.validators.ITypeValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SpaceDTO {

    @ITypeValidator(message = "provided type is unknown")
    private String type;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull(message = "permission group cannot be null")
    @NotBlank(message = "permission group cannot be empty")
    private String permissionGroup;

}
