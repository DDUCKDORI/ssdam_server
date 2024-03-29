package com.dduckdori.ssdam_server.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String access_token;
    private String refresh_token;
    private String invite_cd;
    private int mem_id;
    private String fm_dvcd;//가족 구분자
    private String nick_nm;//닉네임
}
