package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Schedule;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class GetSchedulesRspDto {
    private List<GetScheduleRspDto> scheduleList;

    @Builder
    public GetSchedulesRspDto(List<Schedule> schedules) {
        this.scheduleList = schedules.stream().map(GetScheduleRspDto::new).toList();
    }
}
