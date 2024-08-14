package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Schedule;
import lombok.*;

import static com.bootcamp.schedulemanagementapp.utils.Utils.timeStampToStringDate;

@Getter
@ToString
@NoArgsConstructor
public class GetScheduleRspDto {
    private long scheduleId;
    private String contents;
    private String managerName;
    private String regDate;
    private String updateDate;

    @Builder
    public GetScheduleRspDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.contents = schedule.getContents();
        this.managerName = schedule.getManagerName();
        this.regDate = timeStampToStringDate(schedule.getRegDate());
        this.updateDate = timeStampToStringDate(schedule.getUpdateDate());
    }
}