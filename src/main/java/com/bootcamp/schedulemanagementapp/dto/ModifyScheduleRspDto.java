package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Schedule;
import lombok.*;

import static com.bootcamp.schedulemanagementapp.utils.Utils.timeStampToStringDate;

@Getter
@ToString
@NoArgsConstructor
public class ModifyScheduleRspDto {
    private Long scheduleId;
    private String contents;
    private String managerName;
    private String regDate;
    private String updateDate;

    @Builder
    public ModifyScheduleRspDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.contents = schedule.getContents();
        this.managerName = schedule.getManagerName();
        this.regDate = timeStampToStringDate(schedule.getRegDate());
        this.updateDate = timeStampToStringDate(schedule.getUpdateDate());
    }
}