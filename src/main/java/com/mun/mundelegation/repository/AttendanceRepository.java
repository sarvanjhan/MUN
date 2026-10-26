package com.mun.mundelegation.repository;

import com.mun.mundelegation.Attendance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AttendanceRepository {

    private final JdbcTemplate jdbcTemplate;

    public AttendanceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Attendance> rowMapper = (rs, rowNum) ->
            new Attendance(
                    rs.getLong("id"),
                    rs.getLong("delegate_id"),
                    rs.getString("full_name"),
                    rs.getDate("attendance_date").toLocalDate(),
                    rs.getString("status")
            );

    public List<Attendance> findAll() {
        return jdbcTemplate.query(
                "SELECT a.id, a.delegate_id, d.full_name, a.attendance_date, a.status " +
                "FROM attendance a JOIN delegates d ON a.delegate_id = d.id " +
                "ORDER BY a.attendance_date DESC, d.full_name",
                rowMapper
        );
    }

    public int save(Attendance attendance) {
        return jdbcTemplate.update(
                "INSERT INTO attendance (delegate_id, attendance_date, status) VALUES (?, ?, ?)",
                attendance.getDelegateId(),
                attendance.getAttendanceDate(),
                attendance.getStatus()
        );
    }

    public int update(Attendance attendance) {
        return jdbcTemplate.update(
                "UPDATE attendance SET delegate_id = ?, attendance_date = ?, status = ? WHERE id = ?",
                attendance.getDelegateId(),
                attendance.getAttendanceDate(),
                attendance.getStatus(),
                attendance.getId()
        );
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "DELETE FROM attendance WHERE id = ?", id
        );
    }
}