package com.dduckdori.ssdam_server.Scheduler;

import com.dduckdori.ssdam_server.Mapper.SchedulerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SchedulerRepositoryImplTest {
    private final SchedulerMapper schedulerMapper;
    @Autowired
    public SchedulerRepositoryImplTest(SchedulerMapper schedulerMapper){
        this.schedulerMapper = schedulerMapper;
    }



}