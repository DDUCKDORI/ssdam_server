package com.dduckdori.ssdam_server.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {
    private String result;
    private int non_ans_num;
    private String all_ans_yn;
}
