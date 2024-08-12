package com.bootcamp.schedulemanagementapp.repository;

import com.bootcamp.schedulemanagementapp.entity.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ScheduleJDBCRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Schedule save(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("schedule_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("contents", schedule.getContents());
        parameters.put("manager_name", schedule.getManagerName());
        parameters.put("password", schedule.getPassword());
        parameters.put("reg_date", schedule.getRegDate());
        parameters.put("update_date", schedule.getUpdateDate());
        parameters.put("is_deleted", 0);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        schedule.setScheduleId(key.longValue());
        return schedule;
    }

    @Override
    public Optional<Schedule> findByScheduleId(long scheduleId) {
        String sql = "SELECT schedule_id, contents, manager_name, reg_date, update_date FROM schedule WHERE schedule_id = ? AND is_deleted = false";

        try {
            Schedule result = jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> Schedule.builder()
                            .scheduleId(rs.getLong("schedule_id"))
                            .contents(rs.getString("contents"))
                            .managerName(rs.getString("manager_name"))
                            .regDate(rs.getTimestamp("reg_date"))
                            .updateDate(rs.getTimestamp("update_date"))
                            .build(), scheduleId);

            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Schedule> findAll() {
        String sql = "SELECT schedule_id, contents, manager_name, reg_date, update_date FROM schedule WHERE is_deleted = false ORDER BY update_date DESC";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> Schedule.builder()
                        .scheduleId(rs.getLong("schedule_id"))
                        .contents(rs.getString("contents"))
                        .managerName(rs.getString("manager_name"))
                        .regDate(rs.getTimestamp("reg_date"))
                        .updateDate(rs.getTimestamp("update_date"))
                        .build());
    }

    @Override
    public List<Schedule> findByUpdateDateAndManagerName(String updateDate, String managerName) {
        String sql = "SELECT schedule_id, contents, manager_name, reg_date, update_date FROM schedule WHERE is_deleted = false";
        List<Object> params = new ArrayList<>();

        if (StringUtils.hasText(updateDate)) {
            sql += " AND DATE_FORMAT(update_date, '%Y-%m-%d') = ?";
            params.add(updateDate);
        }

        if (StringUtils.hasText(managerName)) {
            sql += " AND manager_name LIKE ?";
            params.add(managerName);
        }

        sql += " ORDER BY update_date DESC";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> Schedule.builder()
                        .scheduleId(rs.getLong("schedule_id"))
                        .contents(rs.getString("contents"))
                        .managerName(rs.getString("manager_name"))
                        .regDate(rs.getTimestamp("reg_date"))
                        .updateDate(rs.getTimestamp("update_date"))
                        .build(), params.toArray());
    }
}