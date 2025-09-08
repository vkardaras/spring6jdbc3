package com.pluralsight.conference.service;

import com.pluralsight.conference.model.Speaker;

import java.util.List;
import java.util.Optional;

public interface SpeakerService {
    List<Speaker> findAll();

    Speaker create(Speaker speaker);

    Optional<Speaker> findById(Long id);
}
