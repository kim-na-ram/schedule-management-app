package com.bootcamp.schedulemanagementapp.service;

import com.bootcamp.schedulemanagementapp.dto.GetManagerRspDto;
import com.bootcamp.schedulemanagementapp.dto.GetManagersRspDto;
import com.bootcamp.schedulemanagementapp.dto.ModifyManagerReqDto;
import com.bootcamp.schedulemanagementapp.dto.RegisterManagerReqDto;
import com.bootcamp.schedulemanagementapp.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Override
    public void save(RegisterManagerReqDto registerManagerReqDto) {
        validateEmailFormat(registerManagerReqDto.getEmail());

        managerRepository.save(registerManagerReqDto.toEntity());
    }

    @Override
    public GetManagerRspDto findByManagerId(long managerId) {
        return new GetManagerRspDto(managerRepository.findByManagerId(managerId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 담당자입니다.")));
    }

    @Override
    public GetManagersRspDto findAll() {
        return new GetManagersRspDto(managerRepository.findAll());
    }

    @Override
    public void updateByManagerId(long managerId, ModifyManagerReqDto modifyManagerReqDto) {
        boolean isExistSchedule = managerRepository.existsByManagerId(managerId);

        if (!isExistSchedule) {
            throw new NoSuchElementException("존재하지 않는 담당자입니다.");
        }

        validateEmailFormat(modifyManagerReqDto.getEmail());

        boolean result = managerRepository.updateByManagerId(managerId, modifyManagerReqDto.toEntity());
        if(!result) throw new RuntimeException("담당자 수정에 실패하였습니다.");
    }

    private void validateEmailFormat(String email) {
        if(StringUtils.hasText(email)
                && !email.matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new IllegalArgumentException("잘못된 이메일 형식입니다.");
        }
    }
}
