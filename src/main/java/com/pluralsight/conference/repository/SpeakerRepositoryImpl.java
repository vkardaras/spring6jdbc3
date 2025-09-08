package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Speaker;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {

    private JdbcTemplate jdbcTemplate;
    private JdbcClient jdbcClient;

    // RowMapper to map result set to Student object
    private final RowMapper<Speaker> rowMapper = (rs, rowNum) -> {
        Speaker student = new Speaker();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setSkill(rs.getString("skill"));
        return student;
    };

    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate, JdbcClient jdbcClient) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcClient = jdbcClient;
    }

    public List<Speaker> findAll() {
//        Speaker speaker = new Speaker();
//        speaker.setName("Bryan Hansen");
//        speaker.setSkill("Java");
//        List<Speaker> speakers = new ArrayList<>();
//        speakers.add(speaker);
//        return speakers;

//        String sql = "SELECT * FROM speaker";
//        return jdbcTemplate.query(sql, rowMapper);

        return jdbcClient.sql("SELECT id, name, skill FROM speaker")
                .query(Speaker.class)
                .list();
    }

    @Override
    public Speaker create(Speaker speaker) {
        jdbcTemplate.update("INSERT INTO speaker(name) values (?)", speaker.getName());

        return null;
    }

    @Override
    public Optional<Speaker> findById(Long id) {
        String sql = "SELECT * FROM Student WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }
}
