package com.mun.mundelegation.service;

import com.mun.mundelegation.Attendance;
import com.mun.mundelegation.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public void addAttendance(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    public void updateAttendance(Attendance attendance) {
        attendanceRepository.update(attendance);
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}