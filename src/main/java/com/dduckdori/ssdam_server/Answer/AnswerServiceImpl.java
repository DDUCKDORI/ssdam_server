package com.dduckdori.ssdam_server.Answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public int Save_Answer_Hist(AnswerDTO answerDTO) {return answerRepository.Save_Answer_Hist(answerDTO);}

    @Override
    public AnswerDTO[] Find_Answer(String id) {

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
}
