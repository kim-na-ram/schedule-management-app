package com.bootcamp.schedulemanagementapp.repository;

import com.bootcamp.schedulemanagementapp.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    Optional<Schedule> findByScheduleId(long scheduleId);
    List<Schedule> findAll();
    List<Schedule> findByUpdateDateAndManagerName(String updateDate, String managerName);
    boolean existsByScheduleId(long scheduleId);
    boolean updateByScheduleIdAndPassword(long scheduleId, Schedule schedule);
}