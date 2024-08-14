package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.constants.ResponseCode;
import com.bootcamp.schedulemanagementapp.dto.GetManagerRspDto;
import com.bootcamp.schedulemanagementapp.dto.GetManagersRspDto;
import com.bootcamp.schedulemanagementapp.dto.ModifyManagerReqDto;
import com.bootcamp.schedulemanagementapp.dto.RegisterManagerReqDto;
import com.bootcamp.schedulemanagementapp.exception.ApiException;
import com.bootcamp.schedulemanagementapp.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Override
    public void save(RegisterManagerReqDto registerManagerReqDto) {
        managerRepository.save(registerManagerReqDto.toEntity());
    }

    @Override
    public GetManagerRspDto findByManagerId(long managerId) {
        return new GetManagerRspDto(managerRepository.findByManagerId(managerId)
                .orElseThrow(() -> new ApiException(ResponseCode.NOT_EXIST_MANAGER)));
    }

    @Override
    public GetManagersRspDto findAll() {
        return new GetManagersRspDto(managerRepository.findAll());
    }

    @Override
    public void updateByManagerId(long managerId, ModifyManagerReqDto modifyManagerReqDto) {
        boolean isExistSchedule = managerRepository.existsByManagerId(managerId);

        if (!isExistSchedule) {
            throw new ApiException(ResponseCode.NOT_EXIST_MANAGER);
        }

        boolean result = managerRepository.updateByManagerId(managerId, modifyManagerReqDto.toEntity());
        if(!result) throw new ApiException(ResponseCode.FAIL_MODIFY_MANAGER);
    }
}
