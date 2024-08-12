package com.bootcamp.schedulemanagementapp.entity;

import com.bootcamp.schedulemanagementapp.dto.RegisterScheduleRspDto;
import lombok.*;

import java.sql.Timestamp;

import static com.bootcamp.schedulemanagementapp.utils.Utils.timeStampToStringDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class Schedule {
    @Setter
    private Long scheduleId;        // 스케줄 고유번호
    private String contents;        // 할 일 내용
    private String managerName;     // 담당자명
    private String password;        // 비밀번호
    private Timestamp regDate;      // 작성일
    private Timestamp updateDate;   // 수정일
}