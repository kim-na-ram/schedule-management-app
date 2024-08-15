package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Schedule;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ModifyScheduleReqDto {
    private String contents;
    private Long managerId;
    @NotBlank(message = "비밀번호는 필수로 작성해야 합니다.")
    private String password;

    public Schedule toEntity() {
        return Schedule.builder()
                .contents(contents)
                .managerId(managerId)
                .password(password)
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
