package com.bootcamp.schedulemanagementapp.repository;

import com.bootcamp.schedulemanagementapp.entity.Schedule;

import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    Optional<Schedule> findByScheduleId(long scheduleId);
}