package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.Speaker;
import com.pluralsight.conference.service.SpeakerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SpeakerController.class)
class SpeakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpeakerService speakerService;

    @Test
    public void testGetSpeakerById() throws Exception {
        // Create a Speaker object
        Speaker speaker = new Speaker("Vasilis Kardaras", "Java");
        speaker.setId(1L);

        // Setup the mock service to return the Speaker object
        when(speakerService.findById(1L)).thenReturn(Optional.of(speaker));

        // Invoke GET /speaker/1
        mockMvc.perform(get("/speaker/{id}", 1))
                .andExpect(status().isOk())

                // Validate the headers
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the contents of the response
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Vasilis Kardaras"))
                .andExpect(jsonPath("$.skill").value("Java"));
    }

    @Test
    public void testGetSpeakerByIdNotFound() throws Exception {

        // Setup the mock service to return an Optional empty
        when(speakerService.findById(1L)).thenReturn(Optional.empty());

        // Invoke GET /speaker/1
        mockMvc.perform(get("/speaker/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetSpeakers() throws Exception {

        // Create a list of 2 Speakers
        List<Speaker> speakerList = new ArrayList<>();
        speakerList.add(new Speaker("John Doe", "Java"));
        speakerList.add(new Speaker("George Smith", "SQL"));

        // Setup the mock service to return the list
        when(speakerService.findAll()).thenReturn(speakerList);

        // Invoke the GET /speakers endpoint
        mockMvc.perform(get("/speaker"))
                // Validate that we get a 200 OK HTTP Response
                .andExpect(status().isOk())

                // Validate that the response has 2 elements
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

}