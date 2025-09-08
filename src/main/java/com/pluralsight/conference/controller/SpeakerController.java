package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.Speaker;
import com.pluralsight.conference.service.SpeakerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpeakerController {

    private SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @PutMapping("/speaker")
    public Speaker createSpeaker(@RequestBody Speaker speaker) {
        System.out.println("Name: " + speaker.getName());

        return speakerService.create(speaker);
    }

    @GetMapping("/speaker")
    public List<Speaker> getSpeakers() {
        return speakerService.findAll();
    }

    @GetMapping("/speaker/{id}")
    public ResponseEntity<?> getSpeaker(@PathVariable Long id) {
        return speakerService.findById(id)
                .map(speaker -> ResponseEntity.ok().body(speaker))
                .orElse(ResponseEntity.notFound().build());
    }
}
