package com.dduckdori.ssdam_server.Scheduler;

import com.dduckdori.ssdam_server.Mapper.SchedulerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SchedulerRepositoryImpl implements SchedulerRepository {
    private final SchedulerMapper schedulerMapper;
    @Override
    public List<SchedulerDTO> getAllQuestion_notSend() {
        return schedulerMapper.getAllQuestion_notSend();
    }

    @Override
    public int insert_Question(List<SchedulerDTO> input_param) {

        return schedulerMapper.send_Question(input_param);
    }
}
