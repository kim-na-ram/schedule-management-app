package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.dto.GetScheduleRspDto;
import com.bootcamp.schedulemanagementapp.dto.GetSchedulesRspDto;
import com.bootcamp.schedulemanagementapp.dto.RegisterScheduleReqDto;
import com.bootcamp.schedulemanagementapp.dto.RegisterScheduleRspDto;
import com.bootcamp.schedulemanagementapp.repository.ScheduleRepository;
import com.bootcamp.schedulemanagementapp.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public GetSchedulesRspDto findAllOrFindByCondition(String updateDate, String managerName) {
        if(StringUtils.hasText(updateDate)
                && !updateDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new IllegalArgumentException("잘못된 날짜 형식입니다.");
        }

        if(!StringUtils.hasText(managerName) && !StringUtils.hasText(updateDate)) {
            return new GetSchedulesRspDto(scheduleRepository.findAll());
        } else {
            return new GetSchedulesRspDto(scheduleRepository.findByUpdateDateAndManagerName(updateDate, managerName));
        }
    }
}