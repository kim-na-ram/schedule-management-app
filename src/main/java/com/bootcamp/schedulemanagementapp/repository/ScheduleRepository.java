package com.bootcamp.schedulemanagementapp.repository;

import com.bootcamp.schedulemanagementapp.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    Optional<Schedule> findByScheduleId(long scheduleId);
    List<Schedule> findAll(Integer pageNumber, Integer pageSize);
    List<Schedule> findByUpdateDateAndManagerId(Integer pageNumber, Integer pageSize, String updateDate, Long managerId);
    boolean existsByScheduleId(long scheduleId);
    boolean updateByScheduleIdAndPassword(long scheduleId, Schedule schedule);
    boolean deleteByScheduleIdAndPassword(long scheduleId, String password);
}