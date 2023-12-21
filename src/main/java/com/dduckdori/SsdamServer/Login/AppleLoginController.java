package com.dduckdori.SsdamServer.Login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.text.ParseException;


@RestController
@RequiredArgsConstructor
public class AppleLoginController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AppleLoginService appleLoginService;
    @GetMapping("/ssdam")
    public ResponseEntity<LoginDTO> index(){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        LoginDTO loginDTO = new LoginDTO();

        return new ResponseEntity<>(loginDTO,httpHeaders, HttpStatus.OK);
    }
    @RequestMapping(value="/ssdam/login",method=RequestMethod.GET)
    public ResponseEntity<LoginDTO> login(){
        //redirect 시켜야함

        String url = appleLoginService.getRedirectURL();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
    @RequestMapping(value="/ssdam/apple/login/callback",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AppleDTO> applecallback(AppleDTO appleDTO) throws ParseException, IOException, JOSEException, net.minidev.json.parser.ParseException {
        //code
        //id_token
        appleLoginService.getToken(appleDTO);
        appleLoginService.authToken(appleDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(appleDTO, httpHeaders, HttpStatus.OK);
    }
}
