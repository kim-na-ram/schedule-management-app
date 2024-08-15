package com.bootcamp.schedulemanagementapp.dto;

import com.bootcamp.schedulemanagementapp.entity.Manager;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class GetManagersRspDto {
    private List<GetManagerRspDto> managerList;

    @Builder
    public GetManagersRspDto(List<Manager> managers) {
        this.managerList = managers.stream().map(GetManagerRspDto::new).toList();
    }
}
