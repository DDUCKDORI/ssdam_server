package com.dduckdori.ssdam_server.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtcResponse {
    private String result;
    private int family_num;
}
