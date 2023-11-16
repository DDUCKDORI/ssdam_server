package com.dduckdori.ssdam_server.Scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerRepository schedulerRepository;
    @Override
    public ConcurrentHashMap getAllQuestion_notSend(){
        ConcurrentHashMap<String, List<SchedulerDTO>> rciv_qust= new ConcurrentHashMap<>();
        List<SchedulerDTO> l_rciv_qust=schedulerRepository.getAllQuestion_notSend();
        ListIterator<SchedulerDTO> iterator = l_rciv_qust.listIterator();
        while(iterator.hasNext()){
            SchedulerDTO schedulerDTO=iterator.next();
            String invite_cd=schedulerDTO.getInvite_cd();
            int cate_id=schedulerDTO.getCate_id();
            int qust_id=schedulerDTO.getQust_id();
            //hashmap에 invite_cd를 키값으로 집어넣기
            if(rciv_qust.containsKey(invite_cd)){
                //map에 키값이 존재한다면?
                rciv_qust.get(invite_cd).add(new SchedulerDTO(cate_id,qust_id));
            }
            else{
                //map에 키값이 존재하지 않는다면?
                //새 리스트 만들어 넣기
                List<SchedulerDTO> list = new ArrayList<>();
                list.add(new SchedulerDTO(cate_id,qust_id));
                rciv_qust.put(invite_cd, list);
            }
        }
        return rciv_qust;
        //랜덤으로 하나 index 값 중에 하나 선택해서 질문 발송하기
    }

    @Override
    public List<SchedulerDTO> get_Question(ConcurrentHashMap<String,List<SchedulerDTO>> h_map) {
        //키값별로 하나씩 선택해 내기
        //선택해서 [[invite_cd_1,cate_id_1,qust_id_1], [invite_cd_2,cate_id_2,qust_id_2], [invite_cd_3,cate_id_3,qust_id_3] ,,,,]
        //형태로 값 repository 넘겨주기
        List<SchedulerDTO> input_parm=new ArrayList<>();
        Random random = new Random();
        for(String key: h_map.keySet()){
            List<SchedulerDTO> h_map_value=h_map.get(key);
            int value_length=h_map_value.size();
            int random_index=random.nextInt(value_length);
            //index 값 중 임의의 값 하나 선택해서
            String random_invite_cd=key;
            int random_cate_id=h_map_value.get(random_index).getCate_id();
            int random_qust_id=h_map_value.get(random_index).getQust_id();
            input_parm.add(new SchedulerDTO(random_invite_cd,random_cate_id,random_qust_id));
        }
        return input_parm;
    }

    @Override
    public int send_Question(List<SchedulerDTO> send_question) {
        return schedulerRepository.insert_Question(send_question);
    }
}
