package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Schedule;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterScheduleReqDto {
    private String contents;
    private Long managerId;
    private String password;

    public Schedule toEntity() {
        return Schedule.builder()
                .contents(contents)
                .managerId(managerId)
                .password(password)
                .regDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}