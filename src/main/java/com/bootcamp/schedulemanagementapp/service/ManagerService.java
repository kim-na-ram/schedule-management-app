package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.dto.GetManagerRspDto;
import com.bootcamp.schedulemanagementapp.dto.GetManagersRspDto;
import com.bootcamp.schedulemanagementapp.dto.ModifyManagerReqDto;
import com.bootcamp.schedulemanagementapp.dto.RegisterManagerReqDto;

public interface ManagerService {
    void save(RegisterManagerReqDto registerManagerReqDto);
    GetManagerRspDto findByManagerId(long managerId);
    GetManagersRspDto findAll();
    void updateByManagerId(long managerId, ModifyManagerReqDto modifyManagerReqDto);
}