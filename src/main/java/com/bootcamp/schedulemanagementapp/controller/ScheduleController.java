package com.bootcamp.schedulemanagementapp.controller;

import com.bootcamp.schedulemanagementapp.dto.RegisterScheduleReqDto;
import com.bootcamp.schedulemanagementapp.dto.RegisterScheduleRspDto;
import com.bootcamp.schedulemanagementapp.service.ScheduleService;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        } catch (DuplicateRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}