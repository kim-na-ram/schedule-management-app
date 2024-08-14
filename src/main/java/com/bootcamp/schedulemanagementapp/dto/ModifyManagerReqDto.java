package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Manager;
import lombok.*;

import java.sql.Timestamp;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ModifyManagerReqDto {
    private String name;
    @Getter
    private String email;

    public Manager toEntity() {
        return Manager.builder()
                .name(name)
                .email(email)
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}