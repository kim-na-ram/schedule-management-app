package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Schedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterScheduleReqDto {
    @NotBlank(message = "일정은 필수로 작성해야 합니다.")
    @Size(min = 1, max = 200, message = "일정은 1자 이상 200자 이내로 작성해야 합니다.")
    private String contents;
    @NotNull(message = "담당자 고유번호는 필수로 작성해야 합니다.")
    private Long managerId;
    @NotBlank(message = "비밀번호는 필수로 작성해야 합니다.")
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