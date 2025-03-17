package com.example.demo.controllers;


import com.example.demo.dto.ActualMusicDTO;
import com.example.demo.models.LiveExecution;
import com.example.demo.services.LiveExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/live-exec")
public class LiveExecutionController {


    @Autowired
    private LiveExecutionService liveExecutionService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping("/start")
    public ResponseEntity<LiveExecution> startLiveExecution(
            @RequestParam Long bandId,
            @RequestParam Long repertoireId) {
        LiveExecution execution = liveExecutionService.startExecution(bandId, repertoireId);


        ActualMusicDTO actualMusicDTO = ActualMusicDTO.fromEntity(execution.getRepertoire().getMusic());

        messagingTemplate.convertAndSend("/topic/band/" + bandId + "/live-exec",
                actualMusicDTO);
        return ResponseEntity.ok(execution);
    }

    @GetMapping("/actual/{bandId}")
    public ResponseEntity<LiveExecution> getActualLiveExecution(@PathVariable Long bandId) {

        Optional<LiveExecution> executionOptional = liveExecutionService.findActualExecution(bandId);
        if (executionOptional.isPresent()) {
            return ResponseEntity.ok(executionOptional.get());
        } else {
            return ResponseEntity.noContent().build(); // ou .notFound().build();
        }
    }
}