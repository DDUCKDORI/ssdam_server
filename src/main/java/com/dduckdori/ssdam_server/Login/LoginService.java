package com.dduckdori.ssdam_server.Login;

import com.dduckdori.ssdam_server.Exception.UnAuthroizedAccessException;
import com.dduckdori.ssdam_server.Response.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;

import java.io.IOException;
import java.text.ParseException;

public interface LoginService {
    String getRedirectURL();
    LoginDTO getToken(AppleDTO appleDTO) throws ParseException, JsonProcessingException, JOSEException, UnAuthroizedAccessException;
    LoginDTO authToken(AppleDTO appleDTO) throws IOException, net.minidev.json.parser.ParseException, ParseException;
    ResponseDTO joinMember(LoginDTO loginDTO);

    String ReIssueAccessToken(LoginDTO loginDTO) throws IOException, net.minidev.json.parser.ParseException;
}
