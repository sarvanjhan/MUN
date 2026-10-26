package com.mun.mundelegation.repository;

import com.mun.mundelegation.model.Delegate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DelegateRepository {

    private final JdbcTemplate jdbcTemplate;

    public DelegateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Delegate> rowMapper = (rs, rowNum) -> {
        Delegate delegate = new Delegate();
        delegate.setId(rs.getLong("id"));
        delegate.setFullName(rs.getString("full_name"));
        delegate.setEmail(rs.getString("email"));
        delegate.setCollege(rs.getString("college"));
        delegate.setPhone(rs.getString("phone"));
        delegate.setCountryId((Long) rs.getObject("country_id"));
        delegate.setCommitteeId((Long) rs.getObject("committee_id"));
        delegate.setCountry(rs.getString("country_name"));
        delegate.setCommittee(rs.getString("committee_name"));
        delegate.setRegistrationDate(rs.getDate("registration_date").toLocalDate());
        return delegate;
    };

    public List<Delegate> findAll() {
        return jdbcTemplate.query(
            "SELECT d.*, c.name AS country_name, cm.name AS committee_name " +
            "FROM delegates d " +
            "LEFT JOIN countries c ON d.country_id = c.id " +
            "LEFT JOIN committees cm ON d.committee_id = cm.id " +
            "ORDER BY d.id DESC",
            rowMapper
        );
    }

    public void save(Delegate delegate) {
        jdbcTemplate.update(
            "INSERT INTO delegates (full_name, email, college, phone, country, committee, country_id, committee_id, registration_date) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            delegate.getFullName(),
            delegate.getEmail(),
            delegate.getCollege(),
            delegate.getPhone(),
            null,
            null,
            delegate.getCountryId(),
            delegate.getCommitteeId(),
            delegate.getRegistrationDate()
        );
    }

    public Delegate findById(Long id) {
        List<Delegate> delegates = jdbcTemplate.query(
            "SELECT d.*, c.name AS country_name, cm.name AS committee_name " +
            "FROM delegates d " +
            "LEFT JOIN countries c ON d.country_id = c.id " +
            "LEFT JOIN committees cm ON d.committee_id = cm.id " +
            "WHERE d.id = ?",
            rowMapper,
            id
        );

        return delegates.isEmpty() ? null : delegates.get(0);
    }

    public void update(Delegate delegate) {
        jdbcTemplate.update(
            "UPDATE delegates SET full_name=?, email=?, college=?, phone=?, country_id=?, committee_id=? WHERE id=?",
            delegate.getFullName(),
            delegate.getEmail(),
            delegate.getCollege(),
            delegate.getPhone(),
            delegate.getCountryId(),
            delegate.getCommitteeId(),
            delegate.getId()
        );
    }

    public void deleteById(Long id) {
    jdbcTemplate.update(
        "DELETE FROM attendance WHERE delegate_id = ?",
        id
    );

    jdbcTemplate.update(
        "DELETE FROM delegates WHERE id = ?",
        id
    );
    }
}

