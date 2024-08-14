package com.bootcamp.schedulemanagementapp.controller;

import com.bootcamp.schedulemanagementapp.dto.*;
import com.bootcamp.schedulemanagementapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bootcamp.schedulemanagementapp.constants.ManagerApiUrl.*;
import static com.bootcamp.schedulemanagementapp.constants.ResponseCode.SUCCESS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(MANAGER_BASE_URL)
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping(REGISTER_MANAGER)
    public ResponseEntity<?> registerManager(@RequestBody RegisterManagerReqDto registerManagerReqDto) {
        managerService.save(registerManagerReqDto);
        return new ResponseEntity<>(SUCCESS.getResponseMessage(), SUCCESS.getHttpStatus());
    }

    @GetMapping(MANAGER_ID)
    public ResponseEntity<?> getManager(@PathVariable long managerId) {
        GetManagerRspDto getManagerRspDto = managerService.findByManagerId(managerId);
        return new ResponseEntity<>(getManagerRspDto, SUCCESS.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<?> getAllManagers() {
        GetManagersRspDto getManagersRspDto = managerService.findAll();
        return new ResponseEntity<>(getManagersRspDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping(MANAGER_ID)
    public ResponseEntity<?> modifyManager(@PathVariable("managerId") long managerId, @RequestBody ModifyManagerReqDto modifyManagerReqDto) {
        managerService.updateByManagerId(managerId, modifyManagerReqDto);
        return new ResponseEntity<>(SUCCESS.getResponseMessage(), SUCCESS.getHttpStatus());
    }
}
