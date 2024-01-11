package com.dduckdori.ssdam_server.Answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerList {
    private String nick_nm;//닉네임
    private String ans_cn; //답변 내용
    private Timestamp ans_dtm;//답변 시간
}
