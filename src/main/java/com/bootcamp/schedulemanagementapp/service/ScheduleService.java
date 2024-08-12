package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.dto.GetScheduleRspDto;
import com.bootcamp.schedulemanagementapp.dto.RegisterScheduleReqDto;
import com.bootcamp.schedulemanagementapp.dto.RegisterScheduleRspDto;
import com.bootcamp.schedulemanagementapp.repository.ScheduleRepository;
import com.bootcamp.schedulemanagementapp.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    public GetScheduleRspDto findByScheduleId(long scheduleId) {
        return new GetScheduleRspDto(scheduleRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 일정입니다.")));
    }
}