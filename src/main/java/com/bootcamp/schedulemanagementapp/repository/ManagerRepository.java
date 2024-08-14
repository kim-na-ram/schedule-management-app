package com.bootcamp.schedulemanagementapp.repository;

import com.bootcamp.schedulemanagementapp.entity.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {
    void save(Manager manager);
    Optional<Manager> findByManagerId(long managerId);
    List<Manager> findAll();
    boolean existsByManagerId(long managerId);
    boolean updateByManagerId(long managerId, Manager manager);
}