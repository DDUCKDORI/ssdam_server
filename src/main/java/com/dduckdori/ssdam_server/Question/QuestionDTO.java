package com.dduckdori.ssdam_server.Question;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class QuestionDTO {
    String result;
    String Invite_cd;
    int Mem_id;
    int Cate_id;
    int Qust_id;
    String Qust_cn;
    String Ans_cn;
    Timestamp Ans_dtm;
    String Rpy_yn;
    int Non_ans_num;
    public QuestionDTO(int Mem_id, String Invite_cd, int Qust_id, int Cate_id, String Qust_cn, String Ans_cn, Timestamp Ans_Dtm,String Rpy_yn, int Non_ans_num){
        this.Mem_id=Mem_id;
        this.Invite_cd=Invite_cd;
        this.Qust_id=Qust_id;
        this.Cate_id=Cate_id;
        this.Qust_cn=Qust_cn;
        this.Ans_cn=Ans_cn;
        this.Ans_dtm=Ans_Dtm;
        this.Rpy_yn=Rpy_yn;
        this.Non_ans_num=Non_ans_num;
    }
    public QuestionDTO(String Invite_cd, int Mem_id){
        this.Invite_cd=Invite_cd;
        this.Mem_id=Mem_id;
    }
    public QuestionDTO(){}
}
