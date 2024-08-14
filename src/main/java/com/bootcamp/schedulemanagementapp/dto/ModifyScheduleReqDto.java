package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Schedule;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ModifyScheduleReqDto {
    private String contents;
    private String managerName;
    private String password;

    public Schedule toEntity() {
        return Schedule.builder()
                .contents(contents)
                .managerName(managerName)
                .password(password)
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
