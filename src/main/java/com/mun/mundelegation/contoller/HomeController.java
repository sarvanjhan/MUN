package com.mun.mundelegation.contoller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final JdbcTemplate jdbcTemplate;

    public HomeController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/")
    public String home(Model model) {
        int totalDelegates = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM delegates", Integer.class);

        int totalCountries = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM countries", Integer.class);

        int totalCommittees = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM committees", Integer.class);

        int totalAttendance = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM attendance", Integer.class);

        int presentCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM attendance WHERE status = 'Present'",
                Integer.class);

        double attendancePercentage = totalAttendance == 0
                ? 0.0
                : (presentCount * 100.0) / totalAttendance;

        model.addAttribute("totalDelegates", totalDelegates);
        model.addAttribute("totalCountries", totalCountries);
        model.addAttribute("totalCommittees", totalCommittees);
        model.addAttribute("attendancePercentage",
                String.format("%.1f", attendancePercentage));

        return "dashboard";
    }
}