package com.bootcamp.schedulemanagementapp.repository;

import com.bootcamp.schedulemanagementapp.entity.Schedule;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
}