package com.dduckdori.ssdam_server.Answer;

import com.dduckdori.ssdam_server.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;
    @Override
    public int Save_Answer(AnswerDTO answerDTO) {
        answerDTO.setFst_inpr(answerDTO.getInvite_cd()+"_"+answerDTO.getMem_id());
        answerDTO.setLast_updr(answerDTO.getInvite_cd()+"_"+answerDTO.getMem_id());
        return answerRepository.Save_Answer(answerDTO);
    }

    @Override
    public int Save_Answer_Hist(AnswerDTO answerDTO) {
        return answerRepository.Save_Answer_Hist(answerDTO);
    }

    @Override
    public AnswerDTO[] Find_Answer(String id) throws SQLIntegrityConstraintViolationException {

        AnswerDTO answerDTO = new AnswerDTO();
        String[] s = id.split("_");

        //CateId_QustId_InviteCd_MemId
        answerDTO.setCate_id(Integer.parseInt(s[0]));
        answerDTO.setQust_id(Integer.parseInt(s[1]));
        answerDTO.setInvite_cd(s[2]);
        if(s.length==4){
            answerDTO.setMem_id(Integer.parseInt(s[3]));
        }

        return answerRepository.Find_Answer(answerDTO);
    }

    @Override
    public int InviteCd_Ans_YN(String inviteCd) {
        return answerRepository.InviteCd_Ans_yn(inviteCd);
    }

    @Override
    public int Update_Answer(AnswerDTO answerDTO) {
        answerDTO.setLast_updr(answerDTO.getInvite_cd()+"_"+answerDTO.getMem_id());
        // 특정 초대코드에 해당하는 모든 유저가 답변한 상태라면 답변 수정 불가
        // 특정 초대코에 해당하는 유저 답변 여부 판단 필요
        CompleteDTO completeDTO = answerRepository.complete_answer_YN(answerDTO);
        if(completeDTO.getMem_num()==completeDTO.getAnswer_num()){
            throw new NotFoundException("모든 구성원이 답변을 완료했어요..");
        }
        return answerRepository.Update_Answer(answerDTO);
    }
}
