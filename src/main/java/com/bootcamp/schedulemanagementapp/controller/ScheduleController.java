package com.bootcamp.schedulemanagementapp.controller;

import com.bootcamp.schedulemanagementapp.dto.*;
import com.bootcamp.schedulemanagementapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static com.bootcamp.schedulemanagementapp.constants.ScheduleApiUrl.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(SCHEDULE_BASE_URL)
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping(REGISTER_SCHEDULE)
    public ResponseEntity<?> registerSchedule(@RequestBody RegisterScheduleReqDto registerScheduleReqDto) {
        try {
            RegisterScheduleRspDto registerScheduleRspDto = scheduleService.save(registerScheduleReqDto);

            return new ResponseEntity<>(registerScheduleRspDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(SCHEDULE_ID)
    public ResponseEntity<?> getSchedule(@PathVariable("scheduleId") long scheduleId) {
        try {
            GetScheduleRspDto getScheduleRspDto = scheduleService.findByScheduleId(scheduleId);

            return new ResponseEntity<>(getScheduleRspDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("일정 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSchedules(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "updateDate", required = false) String updateDate,
            @RequestParam(value = "managerId", required = false) Long managerId) {
        try {
            GetSchedulesRspDto getSchedulesResDto = scheduleService.findAllOrFindByCondition(pageNumber, pageSize, updateDate, managerId);

            return new ResponseEntity<>(getSchedulesResDto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("일정 목록 조회에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(SCHEDULE_ID)
    public ResponseEntity<?> modifySchedule(@PathVariable("scheduleId") long scheduleId, @RequestBody ModifyScheduleReqDto modifyScheduleReqDto) {
        try {
            ModifyScheduleRspDto modifyScheduleRspDto = scheduleService.updateByScheduleIdAndPassword(scheduleId, modifyScheduleReqDto);
            return new ResponseEntity<>(modifyScheduleRspDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("일정 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(SCHEDULE_ID)
    public ResponseEntity<?> deleteSchedule(@PathVariable("scheduleId") long scheduleId, @RequestBody DeleteScheduleReqDto deleteScheduleReqDto) {
        try {
            scheduleService.deleteByScheduleIdAndPassword(scheduleId, deleteScheduleReqDto);

            return new ResponseEntity<>("정상 처리되었습니다.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("일정 삭제에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}