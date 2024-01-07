package com.dduckdori.ssdam_server.Login;

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
    private String sub;

    public AppleDTO(String s, String s1) {
        this.code =s;
        this.id_token=s1;
    }
}
