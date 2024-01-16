package com.dduckdori.ssdam_server.Answer;

import com.dduckdori.ssdam_server.Exception.NotFoundException;
import com.dduckdori.ssdam_server.Exception.TryAgainException;
import com.dduckdori.ssdam_server.Response.AnswerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    @PostMapping("/ssdam/answer") //답변 저장
    public ResponseEntity<AnswerResponse> Save_Answer(@Valid @RequestBody AnswerDTO answerDTO){
        AnswerResponse answerResponse = new AnswerResponse();
        int result = answerService.Save_Answer(answerDTO);
        if(result != 1){
            throw new TryAgainException("잠시 후 다시 시도해주시기 바랍니다.");
        }
        //이력 업데이트
        answerService.Save_Answer_Hist(answerDTO);
        //반환되는 데이터 만들건데 특정 초대코드에 해당하는 모든 유저 답변 여부 반환해주어야함
        int non_ans_num = answerService.InviteCd_Ans_YN(answerDTO.getInvite_cd());
        answerResponse.setNon_ans_num(non_ans_num);
        if(non_ans_num==0){
            answerResponse.setAll_ans_yn("True");
        }
        else{
            answerResponse.setAll_ans_yn("False");
        }
        answerResponse.setResult("Success");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(answerResponse,httpHeaders, HttpStatus.OK);
    }
    //CateId_QustId_InviteCd_MemId
    @GetMapping("/ssdam/answer/{id}") //나의 답변 조회 & 초대코드별 모든 구성원 답변 조회
    public ResponseEntity<AnswerDTO[]> Read_Answer(@PathVariable String id) throws SQLIntegrityConstraintViolationException {

        String[] s = id.split("_");
        if(!(s.length==3 || s.length==4)){
            throw new TryAgainException("잘못된 요청입니다.");
        }
        AnswerDTO[] answer_list = answerService.Find_Answer(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        if (answer_list.length != 0) {
            for( AnswerDTO answerDTO : answer_list){
                answerDTO.setResult("Success");
            }
        }
        else{
            throw new NotFoundException("해당 질문이 없어요..");
        }
        return new ResponseEntity<>(answer_list,httpHeaders, HttpStatus.OK);
    }

    //나의 답변 수정
    @PatchMapping("/ssdam/answer")
    public ResponseEntity<AnswerResponse> Change_Answer(@RequestBody AnswerDTO answerDTO){

        AnswerResponse answerResponse = new AnswerResponse();
        answerService.Update_Answer(answerDTO);
        answerDTO.setFst_inpr(answerDTO.getInvite_cd()+"_"+answerDTO.getMem_id());
        answerService.Save_Answer_Hist(answerDTO);

        int non_ans_num = answerService.InviteCd_Ans_YN(answerDTO.getInvite_cd());
        answerResponse.setNon_ans_num(non_ans_num);
        if(non_ans_num==0){
            answerResponse.setAll_ans_yn("True");
        }
        else{
            answerResponse.setAll_ans_yn("False");
        }
        answerResponse.setResult("Success");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(answerResponse,httpHeaders, HttpStatus.OK);
    }
}
