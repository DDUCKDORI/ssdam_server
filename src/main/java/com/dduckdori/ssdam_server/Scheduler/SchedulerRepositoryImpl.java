package com.dduckdori.ssdam_server.Scheduler;

import com.dduckdori.ssdam_server.Mapper.SchedulerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SchedulerRepositoryImpl implements SchedulerRepository {
    private final SchedulerMapper schedulerMapper;
    @Autowired
    public SchedulerRepositoryImpl(SchedulerMapper schedulerMapper){
        this.schedulerMapper = schedulerMapper;

    }
    @Override
    public List<SchedulerDTO> getComplete_Answer() {
        return schedulerMapper.getComplete_Answer();
    }

    @Override
    public int insert_Question(List<SchedulerDTO> input_param) {

        return schedulerMapper.send_Question(input_param);
    }

    @Override
    public List<SchedulerDTO> getQuestion_send(Map map) {

        return schedulerMapper.getQuestion_send(map);
    }

    @Override
    public List<SchedulerDTO> getCate_Qust() {
        return schedulerMapper.getCate_Qust();
    }
}
