package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Manager;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.bootcamp.schedulemanagementapp.utils.Utils.timeStampToStringDate;

@Getter
@ToString
@NoArgsConstructor
public class GetManagerRspDto {
    private long managerId;
    private String name;
    private String email;
    private String regDate;
    private String updateDate;

    @Builder
    public GetManagerRspDto(Manager manager) {
        this.managerId = manager.getManagerId();
        this.name = manager.getName();
        this.email = manager.getEmail();
        this.regDate = timeStampToStringDate(manager.getRegDate());
        this.updateDate = timeStampToStringDate(manager.getUpdateDate());
    }
}
