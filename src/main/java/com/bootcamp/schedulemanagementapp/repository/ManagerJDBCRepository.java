package com.bootcamp.schedulemanagementapp.repository;

import com.bootcamp.schedulemanagementapp.entity.Manager;
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
public class ManagerJDBCRepository implements ManagerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Manager manager) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("manager").usingGeneratedKeyColumns("manager_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", manager.getName());
        if (StringUtils.hasText(manager.getEmail()))
            parameters.put("email", manager.getEmail());
        parameters.put("reg_date", manager.getRegDate());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    @Override
    public Optional<Manager> findByManagerId(long managerId) {
        String sql = "SELECT manager_id, name, email, reg_date, update_date FROM manager WHERE manager_id = ?";

        try {
            Manager manager = jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> Manager.builder()
                            .managerId(rs.getLong("manager_id"))
                            .name(rs.getString("name"))
                            .email(rs.getString("email"))
                            .regDate(rs.getTimestamp("reg_date"))
                            .updateDate(rs.getTimestamp("update_date"))
                            .build(), managerId);

            return Optional.ofNullable(manager);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Manager> findAll() {
        String sql = "SELECT * FROM manager";

        return jdbcTemplate.query(sql, (rs, rowNum) -> Manager.builder()
                .managerId(rs.getLong("manager_id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .regDate(rs.getTimestamp("reg_date"))
                .updateDate(rs.getTimestamp("update_date"))
                .build()
        );
    }

    @Override
    public boolean existsByManagerId(long managerId) {
        String sql = "SELECT COUNT(*) FROM manager WHERE manager_id = ?";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, managerId);

        if (result == null) return false;
        return result > 0;
    }

    @Override
    public boolean updateByManagerId(long managerId, Manager manager) {
        String sql = "UPDATE manager SET update_date = ?";
        List<Object> params = new ArrayList<>();
        params.add(manager.getUpdateDate());

        if (StringUtils.hasText(manager.getName())) {
            sql += ", name = ?";
            params.add(manager.getName());
        }

        if (StringUtils.hasText(manager.getEmail())) {
            sql += ", email = ?";
            params.add(manager.getEmail());
        }

        sql += " WHERE manager_id = ?";
        params.add((int) managerId);

        int result = jdbcTemplate.update(sql, params.toArray());
        return result > 0;
    }
}
