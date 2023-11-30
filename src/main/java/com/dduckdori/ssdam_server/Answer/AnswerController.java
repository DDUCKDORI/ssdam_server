package com.dduckdori.ssdam_server.Answer;

import com.dduckdori.ssdam_server.Exception.TryAgainException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    @PostMapping("/ssdam/answer") //답변 저장
    public int Save_Answer(@Valid @RequestBody AnswerDTO answerDTO){
        answerDTO.setFst_inpr(answerDTO.getInvite_cd()+"_"+answerDTO.getMem_id());
        answerDTO.setLast_updr(answerDTO.getInvite_cd()+"_"+answerDTO.getMem_id());
        int result = answerService.Save_Answer(answerDTO);
        if(result != 1){
            throw new TryAgainException("잠시 후 다시 시도해주시기 바랍니다.");
        }
        //이력 업데이트
        answerService.Save_Answer_Hist(answerDTO);
        //반환되는 데이터 만들건데 초대코드별 모든 유저 답변 여부 반환해주어야함
        return 0;
    }
    //CateId_QustId_InviteCd_MemId
    @GetMapping("/ssdam/answer/{id}") //나의 답변 조회 & 초대코드별 모든 구성원 답변 조회
    public ResponseEntity<AnswerDTO[]> Read_Answer(@PathVariable String id){

        String[] s = id.split("_");
        if(!(s.length==3 || s.length==4)){
            throw new TryAgainException("잘못된 요청입니다.");
        }
        AnswerDTO[] answer_list = answerService.Find_Answer(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        if (answer_list != null) {
            for( AnswerDTO answerDTO : answer_list){
                answerDTO.setResult("Success");
            }
        }
        return new ResponseEntity<>(answer_list,httpHeaders, HttpStatus.OK);
    }

    //나의 답변 수정
}
