package com.bootcamp.schedulemanagementapp.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class Manager {
    private Long managerId;         // 담당자 고유번호
    private String name;            // 담당자명
    private String email;           // 이메일
    private Timestamp regDate;      // 작성일
    private Timestamp updateDate;   // 수정일
}