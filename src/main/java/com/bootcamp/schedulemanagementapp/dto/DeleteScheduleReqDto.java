package com.bootcamp.schedulemanagementapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteScheduleReqDto {
    @NotBlank(message = "비밀번호는 필수로 작성해야 합니다.")
    private String password;
}