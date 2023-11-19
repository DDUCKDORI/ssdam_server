package com.dduckdori.ssdam_server.Question;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class QuestionDTO {
    String Invite_cd;
    int Mem_id;
    int Cate_id;
    int Qust_id;
    String Qust_cn;
    String Ans_cn;
    Timestamp Ans_dtm;
    String Rpy_yn;
    int Non_ans_num;

    public QuestionDTO(String Invite_cd, int Mem_id){
        this.Invite_cd=Invite_cd;
        this.Mem_id=Mem_id;
    }
    public QuestionDTO(){}
}
