package com.dduckdori.ssdam_server.Scheduler;

import java.util.List;
import java.util.Map;

public interface SchedulerRepository {
    public List<SchedulerDTO> getComplete_Answer();
    public int insert_Question(List<SchedulerDTO> input_param);


    List<SchedulerDTO> getQuestion_send(Map map);

    List<SchedulerDTO> getCate_Qust();
}
