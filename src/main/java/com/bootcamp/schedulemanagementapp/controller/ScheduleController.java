package com.bootcamp.schedulemanagementapp.controller;

import com.bootcamp.schedulemanagementapp.dto.*;
import com.bootcamp.schedulemanagementapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bootcamp.schedulemanagementapp.constants.ResponseCode.SUCCESS;
import static com.bootcamp.schedulemanagementapp.constants.ScheduleApiUrl.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(SCHEDULE_BASE_URL)
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping(REGISTER_SCHEDULE)
    public ResponseEntity<?> registerSchedule(@RequestBody RegisterScheduleReqDto registerScheduleReqDto) {
        RegisterScheduleRspDto registerScheduleRspDto = scheduleService.save(registerScheduleReqDto);
        return new ResponseEntity<>(registerScheduleRspDto, SUCCESS.getHttpStatus());
    }

    @GetMapping(SCHEDULE_ID)
    public ResponseEntity<?> getSchedule(@PathVariable("scheduleId") long scheduleId) {
        GetScheduleRspDto getScheduleRspDto = scheduleService.findByScheduleId(scheduleId);
        return new ResponseEntity<>(getScheduleRspDto, SUCCESS.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<?> getAllSchedules(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "updateDate", required = false) String updateDate,
            @RequestParam(value = "managerId", required = false) Long managerId) {
        GetSchedulesRspDto getSchedulesResDto = scheduleService.findAllOrFindByCondition(pageNumber, pageSize, updateDate, managerId);
        return new ResponseEntity<>(getSchedulesResDto, SUCCESS.getHttpStatus());
    }

    @PatchMapping(SCHEDULE_ID)
    public ResponseEntity<?> modifySchedule(@PathVariable("scheduleId") long scheduleId, @RequestBody ModifyScheduleReqDto modifyScheduleReqDto) {
        ModifyScheduleRspDto modifyScheduleRspDto = scheduleService.updateByScheduleIdAndPassword(scheduleId, modifyScheduleReqDto);
        return new ResponseEntity<>(modifyScheduleRspDto, SUCCESS.getHttpStatus());
    }

    @DeleteMapping(SCHEDULE_ID)
    public ResponseEntity<?> deleteSchedule(@PathVariable("scheduleId") long scheduleId, @RequestBody DeleteScheduleReqDto deleteScheduleReqDto) {
        scheduleService.deleteByScheduleIdAndPassword(scheduleId, deleteScheduleReqDto);
        return new ResponseEntity<>(SUCCESS.getResponseMessage(), SUCCESS.getHttpStatus());
    }
}