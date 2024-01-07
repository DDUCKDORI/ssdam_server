package com.dduckdori.ssdam_server.Login;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String access_token;
    private String refresh_token;
    private String invite_cd;
    private int mem_id;
    private String fm_dvcd;//가족 구분자
    private String nick_nm;//닉네임
    private String email;
    private String mem_sub;
}
