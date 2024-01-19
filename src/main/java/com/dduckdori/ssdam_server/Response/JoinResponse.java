package com.dduckdori.ssdam_server.Response;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinResponse {
    private String result;
    @Nullable
    private Integer memId;
}
