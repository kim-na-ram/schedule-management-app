package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Manager;
import lombok.*;

import java.sql.Timestamp;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterManagerReqDto {
    private String name;
    private String email;

    public Manager toEntity() {
        return Manager.builder()
                .name(name)
                .email(email)
                .regDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}