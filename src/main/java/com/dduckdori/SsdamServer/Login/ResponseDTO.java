package com.dduckdori.SsdamServer.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResponseDTO {
    private String acces_token;
    private String token_type;
    private Integer expires_in;
    private String refresh_token;
    private String id_token;
}
