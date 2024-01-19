package com.dduckdori.ssdam_server.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull
    private String newCode;
    @NotNull
    private String oldCode;
    @NotNull
    private Integer memId;
    private Integer newId;
}
