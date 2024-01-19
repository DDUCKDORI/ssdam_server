package com.dduckdori.ssdam_server.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCompleteResponse {
    private String result;

    private ArrayList<String> date;
}
