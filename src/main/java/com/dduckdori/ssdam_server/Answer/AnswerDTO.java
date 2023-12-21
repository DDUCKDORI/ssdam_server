package com.dduckdori.ssdam_server.Answer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private String result;

    @NotNull
    private int Cate_id;
    @NotNull
    private int Qust_id;
    @NotNull
    private int Mem_id;
    @NotNull
    @Size(max=10)
    private String Invite_cd;
    @NotNull
    @Size(min=1, max=100)
    private String Ans_cn;
    private Timestamp Ans_dtm;
    private String Fst_inpr;
    private String Last_updr;

}
