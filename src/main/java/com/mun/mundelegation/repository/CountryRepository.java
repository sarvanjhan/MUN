package com.mun.mundelegation.repository;

import com.mun.mundelegation.Country;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CountryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Country> rowMapper = (rs, rowNum) ->
            new Country(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("region")
            );

    public List<Country> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM countries ORDER BY id", rowMapper);
    }

    public Country findById(Long id) {
        List<Country> countries = jdbcTemplate.query(
                "SELECT * FROM countries WHERE id = ?", rowMapper, id);

        return countries.isEmpty() ? null : countries.get(0);
    }

    public boolean existsByName(String name) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM countries WHERE LOWER(name) = LOWER(?)",
                Integer.class,
                name.trim()
        );

        return count != null && count > 0;
    }

    public boolean existsByNameAndNotId(String name, Long id) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM countries WHERE LOWER(name) = LOWER(?) AND id <> ?",
                Integer.class,
                name.trim(),
                id
        );

        return count != null && count > 0;
    }

    public int save(Country country) {
        return jdbcTemplate.update(
                "INSERT INTO countries (name, region) VALUES (?, ?)",
                country.getName().trim(), country.getRegion());
    }

    public int update(Country country) {
        return jdbcTemplate.update(
                "UPDATE countries SET name = ?, region = ? WHERE id = ?",
                country.getName().trim(), country.getRegion(), country.getId());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "DELETE FROM countries WHERE id = ?", id);
    }
}

