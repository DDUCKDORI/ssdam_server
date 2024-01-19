package com.dduckdori.ssdam_server.Scheduler;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerDTO {
    private String Invite_cd;
    private int Mem_num;
    private int Ans_num;
    private int Cate_id;
    private int Qust_id;
    private String Arrive_dtm;
    public SchedulerDTO(String Invite_cd, int Cate_id, int Qust_id){
        this.Invite_cd = Invite_cd;
        this.Cate_id = Cate_id;
        this.Qust_id = Qust_id;
    }
    public SchedulerDTO(String Invite_cd, int Cate_id, int Qust_id, String Arrive_dtm){
        this.Invite_cd = Invite_cd;
        this.Cate_id = Cate_id;
        this.Qust_id = Qust_id;
        this.Arrive_dtm = Arrive_dtm;
    }
}
