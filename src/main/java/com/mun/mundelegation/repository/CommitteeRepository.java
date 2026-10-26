package com.mun.mundelegation.repository;

import com.mun.mundelegation.Committee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CommitteeRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommitteeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Committee> rowMapper = (rs, rowNum) ->
            new Committee(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("full_name")
            );

    public List<Committee> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM committees ORDER BY id", rowMapper);
    }

    public Committee findById(Long id) {
        List<Committee> committees = jdbcTemplate.query(
                "SELECT * FROM committees WHERE id = ?", rowMapper, id);

        return committees.isEmpty() ? null : committees.get(0);
    }

    public int save(Committee committee) {
        return jdbcTemplate.update(
                "INSERT INTO committees (name, full_name) VALUES (?, ?)",
                committee.getName(), committee.getFullName());
    }

    public int update(Committee committee) {
        return jdbcTemplate.update(
                "UPDATE committees SET name = ?, full_name = ? WHERE id = ?",
                committee.getName(), committee.getFullName(), committee.getId());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "DELETE FROM committees WHERE id = ?", id);
    }
}

