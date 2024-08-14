package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.dto.*;

public interface ScheduleService {
    RegisterScheduleRspDto save(RegisterScheduleReqDto registerScheduleReqDto);
    GetScheduleRspDto findByScheduleId(long scheduleId);
    GetSchedulesRspDto findAllOrFindByCondition(String updateDate, String managerName);
    ModifyScheduleRspDto updateByScheduleIdAndPassword(long scheduleId, ModifyScheduleReqDto modifyScheduleReqDto);
    void deleteByScheduleIdAndPassword(long scheduleId, DeleteScheduleReqDto deleteScheduleReqDto);
}