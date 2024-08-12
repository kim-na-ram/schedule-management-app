package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.dto.RegisterScheduleReqDto;
import com.bootcamp.schedulemanagementapp.dto.RegisterScheduleRspDto;
import com.bootcamp.schedulemanagementapp.repository.ScheduleRepository;
import com.bootcamp.schedulemanagementapp.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public RegisterScheduleRspDto save(RegisterScheduleReqDto registerScheduleReqDto) {
        Schedule savedSchedule;
        try {
            savedSchedule = scheduleRepository.save(registerScheduleReqDto.dtoToScheduleEntity());
            return new RegisterScheduleRspDto(savedSchedule);
        } catch (Exception e) {
            throw new RuntimeException("일정 등록에 실패하였습니다.");
        }
    }
}