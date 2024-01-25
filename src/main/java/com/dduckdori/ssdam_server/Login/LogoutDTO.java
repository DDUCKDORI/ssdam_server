package com.dduckdori.ssdam_server.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutDTO {
    private String authorization_code;
    private String invite_cd;
    private int mem_id;

}
