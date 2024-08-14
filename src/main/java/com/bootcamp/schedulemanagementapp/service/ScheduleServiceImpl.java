package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.constants.ResponseCode;
import com.bootcamp.schedulemanagementapp.dto.*;
import com.bootcamp.schedulemanagementapp.entity.Schedule;
import com.bootcamp.schedulemanagementapp.exception.ApiException;
import com.bootcamp.schedulemanagementapp.repository.ManagerRepository;
import com.bootcamp.schedulemanagementapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ManagerRepository managerRepository;

    @Override
    public RegisterScheduleRspDto save(RegisterScheduleReqDto registerScheduleReqDto) {
        boolean isExistManager = managerRepository.existsByManagerId(registerScheduleReqDto.getManagerId());

        if (!isExistManager) {
            throw new ApiException(ResponseCode.NOT_EXIST_MANAGER);
        }

        try {
            Schedule schedule = scheduleRepository.save(registerScheduleReqDto.toEntity());
            return new RegisterScheduleRspDto(schedule);
        } catch (Exception e) {
            throw new ApiException(ResponseCode.FAIL_REGISTER_SCHEDULE);
        }
    }

    @Override
    public GetScheduleRspDto findByScheduleId(long scheduleId) {
        return new GetScheduleRspDto(scheduleRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new ApiException(ResponseCode.NOT_EXIST_SCHEDULE)));
    }

    @Override
    public GetSchedulesRspDto findAllOrFindByCondition(Integer pageNumber, Integer pageSize, String updateDate, Long managerId) {
        if (StringUtils.hasText(updateDate)
                && !updateDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new ApiException(ResponseCode.INVALID_DATE_FORMAT);
        }

        if(managerId != null && !StringUtils.hasText(updateDate)) {
            return new GetSchedulesRspDto(scheduleRepository.findAll(pageNumber, pageSize));
        } else {
            return new GetSchedulesRspDto(scheduleRepository.findByUpdateDateAndManagerId(pageNumber, pageSize, updateDate, managerId));
        }
    }

    @Override
    public ModifyScheduleRspDto updateByScheduleIdAndPassword(long scheduleId, ModifyScheduleReqDto modifyScheduleReqDto) {
        boolean isExistSchedule = scheduleRepository.existsByScheduleId(scheduleId);

        if (!isExistSchedule) {
            throw new ApiException(ResponseCode.NOT_EXIST_SCHEDULE);
        }

        boolean result = scheduleRepository.updateByScheduleIdAndPassword(scheduleId, modifyScheduleReqDto.toEntity());
        if (result) {
            return new ModifyScheduleRspDto(scheduleRepository.findByScheduleId(scheduleId)
                    .orElseThrow(() -> new ApiException(ResponseCode.NOT_EXIST_SCHEDULE)));
        } else {
            throw new ApiException(ResponseCode.MISMATCH_PASSWORD);
        }
    }

    @Override
    public void deleteByScheduleIdAndPassword(long scheduleId, DeleteScheduleReqDto deleteScheduleReqDto) {
        boolean isExistSchedule = scheduleRepository.existsByScheduleId(scheduleId);

        if (!isExistSchedule) {
            throw new ApiException(ResponseCode.NOT_EXIST_SCHEDULE);
        }

        boolean result = scheduleRepository.deleteByScheduleIdAndPassword(scheduleId, deleteScheduleReqDto.getPassword());
        if (!result) {
            throw new ApiException(ResponseCode.MISMATCH_PASSWORD);
        }
    }

}
