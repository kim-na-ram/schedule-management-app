package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Manager;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ModifyManagerReqDto {
    private String name;
    @Getter
    @Email(message = "잘못된 이메일 형식입니다.")
    private String email;

    public Manager toEntity() {
        return Manager.builder()
                .name(name)
                .email(email)
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}