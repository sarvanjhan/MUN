package com.mun.mundelegation.contoller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ReportController {

private final JdbcTemplate jdbcTemplate;

public ReportController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}

@GetMapping("/reports")
public String reports(Model model) {

    int totalDelegates = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM delegates", Integer.class);

    int totalCountries = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM countries", Integer.class);

    int totalCommittees = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM committees", Integer.class);

    int presentCount = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM attendance WHERE status = 'Present'",
            Integer.class);

    int absentCount = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM attendance WHERE status = 'Absent'",
            Integer.class);

    int totalAttendance = presentCount + absentCount;

    double attendancePercentage = totalAttendance == 0
            ? 0.0
            : (presentCount * 100.0) / totalAttendance;

    List<Map<String, Object>> delegateAttendance = jdbcTemplate.queryForList(
            "SELECT d.id, d.full_name, " +
            "COUNT(a.id) AS total_records, " +
            "SUM(CASE WHEN a.status = 'Present' THEN 1 ELSE 0 END) AS present_records, " +
            "SUM(CASE WHEN a.status = 'Absent' THEN 1 ELSE 0 END) AS absent_records, " +
            "CASE WHEN COUNT(a.id) = 0 THEN 0.0 " +
            "ELSE SUM(CASE WHEN a.status = 'Present' THEN 1.0 ELSE 0.0 END) * 100.0 / COUNT(a.id) " +
            "END AS attendance_percentage " +
            "FROM delegates d " +
            "LEFT JOIN attendance a ON d.id = a.delegate_id " +
            "GROUP BY d.id, d.full_name " +
            "ORDER BY d.full_name"
    );

    model.addAttribute("totalDelegates", totalDelegates);
    model.addAttribute("totalCountries", totalCountries);
    model.addAttribute("totalCommittees", totalCommittees);
    model.addAttribute("presentCount", presentCount);
    model.addAttribute("absentCount", absentCount);
    model.addAttribute("attendancePercentage",
            String.format(java.util.Locale.US, "%.1f", attendancePercentage));
    model.addAttribute("delegateAttendance", delegateAttendance);

    return "reports";
}

}
