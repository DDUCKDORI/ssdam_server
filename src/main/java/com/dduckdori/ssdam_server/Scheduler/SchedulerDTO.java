package com.dduckdori.ssdam_server.Scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerDTO {
    private String Invite_cd;
    private int Cate_id;
    private int Qust_id;

    public SchedulerDTO(int cateId, int qustId) {
        this.Cate_id=cateId;
        this.Qust_id=qustId;
    }
}
