package com.mun.mundelegation;

import java.time.LocalDate;

public class Attendance {
    private Long id;
    private Long delegateId;
    private String delegateName;
    private LocalDate attendanceDate;
    private String status;

    public Attendance() {
    }

    public Attendance(Long id, Long delegateId, String delegateName, LocalDate attendanceDate, String status) {
        this.id = id;
        this.delegateId = delegateId;
        this.delegateName = delegateName;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDelegateId() {
        return delegateId;
    }

    public void setDelegateId(Long delegateId) {
        this.delegateId = delegateId;
    }

    public String getDelegateName() {
        return delegateName;
    }

    public void setDelegateName(String delegateName) {
        this.delegateName = delegateName;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}