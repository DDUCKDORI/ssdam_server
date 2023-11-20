package com.dduckdori.ssdam_server.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String result;
    private String message;


}
