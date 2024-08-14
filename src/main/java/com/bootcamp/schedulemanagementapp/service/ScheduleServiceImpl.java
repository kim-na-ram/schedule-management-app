package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.dto.*;
import com.bootcamp.schedulemanagementapp.entity.Schedule;
import com.bootcamp.schedulemanagementapp.repository.ManagerRepository;
import com.bootcamp.schedulemanagementapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ManagerRepository managerRepository;

    @Override
    public RegisterScheduleRspDto save(RegisterScheduleReqDto registerScheduleReqDto) {
        boolean isExistManager = managerRepository.existsByManagerId(registerScheduleReqDto.getManagerId());

        if (!isExistManager) {
            throw new NoSuchElementException("존재하지 않는 담당자입니다.");
        }

        try {
            Schedule schedule = scheduleRepository.save(registerScheduleReqDto.toEntity());
            return new RegisterScheduleRspDto(schedule);
        } catch (Exception e) {
            throw new RuntimeException("일정 등록에 실패하였습니다.");
        }
    }

    @Override
    public GetScheduleRspDto findByScheduleId(long scheduleId) {
        return new GetScheduleRspDto(scheduleRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 일정입니다.")));
    }

    @Override
    public GetSchedulesRspDto findAllOrFindByCondition(String updateDate, String managerId) {
        if(StringUtils.hasText(updateDate)
                && !updateDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new IllegalArgumentException("잘못된 날짜 형식입니다.");
        }

        if(!StringUtils.hasText(managerId) && !StringUtils.hasText(updateDate)) {
            return new GetSchedulesRspDto(scheduleRepository.findAll());
        } else {
            return new GetSchedulesRspDto(scheduleRepository.findByUpdateDateAndManagerId(updateDate, managerId));
        }
    }

    @Override
    public ModifyScheduleRspDto updateByScheduleIdAndPassword(long scheduleId, ModifyScheduleReqDto modifyScheduleReqDto) {
        boolean isExistSchedule = scheduleRepository.existsByScheduleId(scheduleId);

        if (!isExistSchedule) {
            throw new NoSuchElementException("존재하지 않는 일정입니다.");
        }

        boolean result = scheduleRepository.updateByScheduleIdAndPassword(scheduleId, modifyScheduleReqDto.toEntity());
        if(result) {
            return new ModifyScheduleRspDto(scheduleRepository.findByScheduleId(scheduleId)
                    .orElseThrow(() -> new NoSuchElementException("존재하지 않는 일정입니다.")));
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public void deleteByScheduleIdAndPassword(long scheduleId, DeleteScheduleReqDto deleteScheduleReqDto) {
        boolean isExistSchedule = scheduleRepository.existsByScheduleId(scheduleId);

        if (!isExistSchedule) {
            throw new NoSuchElementException("존재하지 않는 일정입니다.");
        }

        boolean result = scheduleRepository.deleteByScheduleIdAndPassword(scheduleId, deleteScheduleReqDto.getPassword());
        if(!result) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

}
