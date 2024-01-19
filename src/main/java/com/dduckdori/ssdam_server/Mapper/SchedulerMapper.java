package com.dduckdori.ssdam_server.Mapper;

import com.dduckdori.ssdam_server.Scheduler.SchedulerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SchedulerMapper {
    List<SchedulerDTO> getCate_Qust();

    List<SchedulerDTO> getComplete_Answer();

    int send_Question(List<SchedulerDTO> input_param);

    List<SchedulerDTO> getQuestion_send(Map map);
}
