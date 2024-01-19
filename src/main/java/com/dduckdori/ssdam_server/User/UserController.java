package com.dduckdori.ssdam_server.User;

import com.dduckdori.ssdam_server.Answer.AnswerDTO;
import com.dduckdori.ssdam_server.Response.AnswerResponse;
import com.dduckdori.ssdam_server.Response.JoinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/ssdam/join") //가족 참여
    public ResponseEntity<JoinResponse> Change_Answer(@RequestBody UserDTO userDTO) {
        JoinResponse joinResponse = new JoinResponse();

        int newId = userService.find_max_id(userDTO.getNewCode()) + 1;

        userDTO.setNewId(newId);

        userService.delete_user_answer(userDTO);
        userService.delete_answer_history(userDTO);
        userService.join_with_code(userDTO);

        joinResponse.setResult("Success");
        joinResponse.setMemId(newId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(joinResponse, httpHeaders, HttpStatus.OK);
    }
}
