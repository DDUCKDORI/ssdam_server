package com.dduckdori.SsdamServer.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppleDTO {
    private String code;
    private String id_token;

    private String kid;

    public AppleDTO(String s, String s1) {
        this.code =s;
        this.id_token=s1;
    }
}
