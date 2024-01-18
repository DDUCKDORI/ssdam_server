package com.dduckdori.ssdam_server.Answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteDTO {
    private String Arrive_dtm;
    private String Invite_cd;
    private int Mem_num;
    private int Answer_num;
}
