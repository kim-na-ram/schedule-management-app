package com.bootcamp.schedulemanagementapp.controller;

import com.bootcamp.schedulemanagementapp.dto.*;
import com.bootcamp.schedulemanagementapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static com.bootcamp.schedulemanagementapp.constants.ManagerApiUrl.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(MANAGER_BASE_URL)
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping(REGISTER_MANAGER)
    public ResponseEntity<?> registerManager(@RequestBody RegisterManagerReqDto registerManagerReqDto) {
        try {
            managerService.save(registerManagerReqDto);

            return new ResponseEntity<>("정상 처리되었습니다.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("담당자 등록에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(MANAGER_ID)
    public ResponseEntity<?> getManager(@PathVariable long managerId) {
        try {
            GetManagerRspDto getManagerRspDto = managerService.findByManagerId(managerId);

            return new ResponseEntity<>(getManagerRspDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("담당자 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping
    public ResponseEntity<?> getAllManagers() {
        try {
            GetManagersRspDto getManagersRspDto = managerService.findAll();

            return new ResponseEntity<>(getManagersRspDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("담당자 목록 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(MANAGER_ID)
    public ResponseEntity<?> modifyManager(@PathVariable("managerId") long managerId, @RequestBody ModifyManagerReqDto modifyManagerReqDto) {
        try {
            managerService.updateByManagerId(managerId, modifyManagerReqDto);

            return new ResponseEntity<>("정상 처리되었습니다.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("담당자 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
