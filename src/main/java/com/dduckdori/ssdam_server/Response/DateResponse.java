package com.dduckdori.ssdam_server.Response;

import com.dduckdori.ssdam_server.Answer.AnswerList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateResponse {
    private String result;
    private String cate_id;
    private String qust_id;
    private String qust_cn;
    private String invite_cd;
    private AnswerList[] ans_list;
}
