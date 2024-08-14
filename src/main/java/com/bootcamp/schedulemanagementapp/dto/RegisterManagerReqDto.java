package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Manager;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterManagerReqDto {
    @NotBlank(message = "담당자명은 필수로 작성해야 합니다.")
    private String name;
    @Email(message = "잘못된 이메일 형식입니다.")
    private String email;

    public Manager toEntity() {
        return Manager.builder()
                .name(name)
                .email(email)
                .regDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}