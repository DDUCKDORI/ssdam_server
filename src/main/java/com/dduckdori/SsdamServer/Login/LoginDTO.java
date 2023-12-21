package com.dduckdori.SsdamServer.Login;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String id;
    private String token;
    private String email;
}
