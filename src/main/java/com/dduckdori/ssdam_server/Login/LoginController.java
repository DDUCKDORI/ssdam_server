package com.dduckdori.ssdam_server.Login;

import com.dduckdori.ssdam_server.Exception.ExceptionResponse;
import com.dduckdori.ssdam_server.Exception.UnAuthroizedAccessException;
import com.dduckdori.ssdam_server.Response.ResponseDTO;
import com.dduckdori.ssdam_server.Scheduler.SchedulerService;
import com.nimbusds.jose.JOSEException;
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
public class LoginController {

    private final LoginService loginService;
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }
    @RequestMapping(value="/ssdam/login",method=RequestMethod.GET)
    public ResponseEntity<LoginDTO> login(){
        String url = loginService.getRedirectURL();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));
        headers.setContentType(MediaType.APPLICATION_JSON);
        //redirect 시켜야함
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
    @RequestMapping(value="/ssdam/apple/login/callback",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LoginDTO> appleCallBack(@RequestBody AppleDTO appleDTO) throws ParseException, IOException, JOSEException, UnAuthroizedAccessException, net.minidev.json.parser.ParseException {
        //code
        //id_token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        //여기서 sub 값으로 데이터베이스에 해당 sub 값이 있는지 판단

        LoginDTO loginDTO = loginService.getToken(appleDTO);


        //있다면 refresh_token이랑 사용자 정보반환
        if(loginDTO!=null){
            //DB에 sub값이 있다면
            //refresh_token으로 access_token TODO 재발급 받고

            String reIssueAccessToken = loginService.ReIssueAccessToken(loginDTO);

            loginDTO.setAccess_token(reIssueAccessToken);
            loginDTO.setExists_yn("yes");
            // loginDTO 에 담아 반환.
            return new ResponseEntity<>(loginDTO,httpHeaders,HttpStatus.OK);
        }
        //없다면 프로세스 진행 -> sub랑 Refresh_token 저장 -> Okay

        loginDTO = loginService.authToken(appleDTO);
        loginDTO.setExists_yn("no");
        return new ResponseEntity<>(loginDTO, httpHeaders, HttpStatus.OK);
    }
    //refresh_token 저장과 회원 정보는 이후 API 에서 진행
    @RequestMapping(value = "/ssdam/login",method=RequestMethod.POST)
    public ResponseEntity<ResponseDTO> join(@RequestBody LoginDTO loginDTO){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseDTO responseDTO = loginService.joinMember(loginDTO);
        //SD_SEND_DETLSD(발송내역 테이블)에 1,1 질문 추가
        System.out.println("responseDTO = " + responseDTO);
        return new ResponseEntity<>(responseDTO,httpHeaders,HttpStatus.OK);
    }
    // 로그아웃 -> 멤버 토큰 -> ans_hist -> ans -> sd_send_detlsd(선택) ->member
    @RequestMapping(value = "/ssdam/logout",method = RequestMethod.POST)
    public ResponseEntity<ExceptionResponse> logout(@RequestBody LogoutDTO logoutDTO) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        loginService.logout_member(logoutDTO);
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setResult("Success");
        return new ResponseEntity<>(exceptionResponse,httpHeaders,HttpStatus.OK);
    }
}
