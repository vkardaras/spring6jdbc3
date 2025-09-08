package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Speaker;

import java.util.List;
import java.util.Optional;

public interface SpeakerRepository {
    List<Speaker> findAll();

    Speaker create(Speaker speaker);

    Optional<Speaker> findById(Long id);
}
