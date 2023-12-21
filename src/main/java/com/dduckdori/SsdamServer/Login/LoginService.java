package com.dduckdori.SsdamServer.Login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;

import java.io.IOException;
import java.text.ParseException;

public interface LoginService {
    String getRedirectURL();
    String getToken(AppleDTO appleDTO) throws ParseException, JsonProcessingException, JOSEException;
    String authToken(AppleDTO appleDTO) throws IOException, net.minidev.json.parser.ParseException, ParseException;
}
