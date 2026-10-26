package com.mun.mundelegation.contoller;

import com.mun.mundelegation.Attendance;
import com.mun.mundelegation.service.AttendanceService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {


private final AttendanceService attendanceService;
private final JdbcTemplate jdbcTemplate;

public AttendanceController(AttendanceService attendanceService,
                            JdbcTemplate jdbcTemplate) {
    this.attendanceService = attendanceService;
    this.jdbcTemplate = jdbcTemplate;
}

private void addDelegates(Model model) {
    model.addAttribute("delegates",
            jdbcTemplate.queryForList(
                    "SELECT id, full_name FROM delegates ORDER BY full_name"));
}

@GetMapping
public String listAttendance(Model model) {
    model.addAttribute("attendanceList",
            attendanceService.getAllAttendance());

    addDelegates(model);

    return "attendance/list";
}

@GetMapping("/new")
public String showAddForm(Model model) {
    Attendance attendance = new Attendance();
    attendance.setAttendanceDate(LocalDate.now());

    model.addAttribute("attendance", attendance);
    addDelegates(model);

    return "attendance/form";
}

@PostMapping("/save")
public String saveAttendance(@ModelAttribute Attendance attendance,
                             Model model) {
    try {
        attendanceService.addAttendance(attendance);
        return "redirect:/attendance";
    } catch (DuplicateKeyException e) {
        model.addAttribute("attendance", attendance);
        model.addAttribute("error",
                "Attendance has already been recorded for this delegate on the selected date.");

        addDelegates(model);

        return "attendance/form";
    }
}

@GetMapping("/edit/{id}")
public String showEditForm(@PathVariable Long id, Model model) {
    Attendance attendance = jdbcTemplate.queryForObject(
            "SELECT id, delegate_id, attendance_date, status FROM attendance WHERE id = ?",
            (rs, rowNum) -> {
                Attendance a = new Attendance();
                a.setId(rs.getLong("id"));
                a.setDelegateId(rs.getLong("delegate_id"));
                a.setAttendanceDate(
                        rs.getDate("attendance_date").toLocalDate());
                a.setStatus(rs.getString("status"));
                return a;
            }, id);

    model.addAttribute("attendance", attendance);
    addDelegates(model);

    return "attendance/form";
}

@PostMapping("/update")
public String updateAttendance(@ModelAttribute Attendance attendance,
                               Model model) {
    try {
        attendanceService.updateAttendance(attendance);
        return "redirect:/attendance";
    } catch (DuplicateKeyException e) {
        model.addAttribute("attendance", attendance);
        model.addAttribute("error",
                "This delegate already has an attendance record for the selected date.");

        addDelegates(model);

        return "attendance/form";
    }
}

@PostMapping("/delete/{id}")
public String deleteAttendance(@PathVariable Long id) {
    attendanceService.deleteAttendance(id);
    return "redirect:/attendance";
}


}
