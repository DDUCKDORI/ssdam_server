package com.dduckdori.ssdam_server.Scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Controller
@RequiredArgsConstructor
public class SchedulerController {

    private final SchedulerService schedulerService;
    private static ConcurrentHashMap<String,List<SchedulerDTO>> h_map;
    private static List<SchedulerDTO> send_question;

    //todo 배포 시 적용해야할 부분1
    //@Scheduled(cron="0 0 11 * * *",zone="Asia/Seoul") //매일 오전 11시에 실행
    @Scheduled(cron = "0 0/1 0-23 * * *",zone="Asia/Seoul") //5분마다 실행.
    public void findall(){
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        //모든 유저가 답변 완료한 초대코드 뽑아내기
        h_map= schedulerService.getAllQuestion_notSend();
        System.out.println("h_map = " + h_map);
        //안보낸 질문들 선별
        //send_question = schedulerService.get_Question(h_map);
        //schedulerService.send_Question(send_question);
    }

}
