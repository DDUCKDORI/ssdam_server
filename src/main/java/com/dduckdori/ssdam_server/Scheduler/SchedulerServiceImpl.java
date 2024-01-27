package com.dduckdori.ssdam_server.Scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerRepository schedulerRepository;
    @Override
    public ConcurrentHashMap getAllQuestion_notSend(){
        ConcurrentHashMap<String, List<SchedulerDTO>> rciv_qust= new ConcurrentHashMap<>();
        List<SchedulerDTO> l_rciv_qust=schedulerRepository.getComplete_Answer();
        ListIterator<SchedulerDTO> iterator = l_rciv_qust.listIterator();

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formateNow = now.format(formatter);

        List<String> complete_answer = new ArrayList<>();

        while(iterator.hasNext()) {
            SchedulerDTO schedulerDTO = iterator.next();
            //구성원 모두가 답변 완료한 초대코드 뽑아내기

            if(!formateNow.equals(schedulerDTO.getArrive_dtm())){
                System.out.println("schedulerDTO = " + schedulerDTO);
                System.out.println("formateNow = " + formateNow);
                if (schedulerDTO.getMem_num() == schedulerDTO.getAns_num()) {
                    complete_answer.add(schedulerDTO.getInvite_cd());

                }
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("complete_answer",complete_answer);
        if(complete_answer.size()==0){
            return null;
        }
        //답변 완료한 초대코드에 대해 발송하지 않은 질문들 뽑아내기
        l_rciv_qust = schedulerRepository.getQuestion_send(map);

        for(int i=0;i<l_rciv_qust.size();i++){

            String invite_cd = l_rciv_qust.get(i).getInvite_cd();
            int cate_id = l_rciv_qust.get(i).getCate_id();
            int qust_id = l_rciv_qust.get(i).getQust_id();
            if(rciv_qust.containsKey(invite_cd)){
                //map에 키값이 존재한다면?
                rciv_qust.get(invite_cd).add(new SchedulerDTO(invite_cd,cate_id,qust_id));
            }
            else{
                //map에 키값이 존재하지 않는다면?
                //새 리스트 만들어 넣기
                List<SchedulerDTO> list = new ArrayList<>();
                list.add(new SchedulerDTO(invite_cd,cate_id,qust_id));
                rciv_qust.put(invite_cd, list);
            }
        }

        return rciv_qust;
        //랜덤으로 하나 index 값 중에 하나 선택해서 질문 발송하기
    }

    @Override
    public List<SchedulerDTO> get_Question(ConcurrentHashMap<String,List<SchedulerDTO>> h_map) {

        List<SchedulerDTO> input_parm=new ArrayList<>();
        Random random = new Random();
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formateNow = now.format(formatter);

        for(String key: h_map.keySet()){
            List<SchedulerDTO> h_map_value=h_map.get(key);
            int value_length=h_map_value.size();
            int random_index=random.nextInt(value_length);
            //index 값 중 임의의 값 하나 선택해서
            String random_invite_cd=key;
            int random_cate_id=h_map_value.get(random_index).getCate_id();
            int random_qust_id=h_map_value.get(random_index).getQust_id();
            input_parm.add(new SchedulerDTO(random_invite_cd,random_cate_id,random_qust_id,formateNow));
        }
        return input_parm;
    }

    @Override
    public int send_Question(List<SchedulerDTO> send_question) {

        return schedulerRepository.insert_Question(send_question);
    }
}
