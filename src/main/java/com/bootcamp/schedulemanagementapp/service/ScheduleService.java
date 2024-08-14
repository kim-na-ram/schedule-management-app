package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.dto.*;

public interface ScheduleService {
    RegisterScheduleRspDto save(RegisterScheduleReqDto registerScheduleReqDto);
    GetScheduleRspDto findByScheduleId(long scheduleId);
    GetSchedulesRspDto findAllOrFindByCondition(Integer pageNumber, Integer pageSize, String updateDate, Long managerName);
    ModifyScheduleRspDto updateByScheduleIdAndPassword(long scheduleId, ModifyScheduleReqDto modifyScheduleReqDto);
    void deleteByScheduleIdAndPassword(long scheduleId, DeleteScheduleReqDto deleteScheduleReqDto);
}