package com.dduckdori.ssdam_server.Scheduler;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface SchedulerService {
    public ConcurrentHashMap getAllQuestion_notSend();
    public List<SchedulerDTO> get_Question(ConcurrentHashMap<String,List<SchedulerDTO>> h_map);
    public int send_Question(List<SchedulerDTO> send_question);
}
