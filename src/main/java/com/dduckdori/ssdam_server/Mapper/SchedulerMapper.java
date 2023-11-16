package com.dduckdori.ssdam_server.Mapper;

import com.dduckdori.ssdam_server.Scheduler.SchedulerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchedulerMapper {
    List<SchedulerDTO> getAllQuestion_notSend();

    int send_Question(List<SchedulerDTO> input_param);
}
