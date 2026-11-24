package com.aircall.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aircall.dashboard.model.Call;
import com.aircall.dashboard.service.CallService;

@RestController
@RequestMapping("/calls")
public class CallController {

    private final CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping
    public Map<String, List<Call>> getAllCalls() {
        return Map.of("calls", callService.getAllCalls());
    }
    @GetMapping("/{callId}")
    public ResponseEntity<?> getCallById(@PathVariable String callId) {
        return callService.getCallById(callId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error", "Call not found")));
    }
    @PatchMapping("/{callId}/archive")
    public ResponseEntity<?> archiveCall(@PathVariable String callId) {	
        return callService.archiveCall(callId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error", "Call not found")));
    }
    @PostMapping("/{callId}/notes")
    public ResponseEntity<?> addNoteToCall(
            @PathVariable String callId,
            @RequestBody(required = false) Map<String, String> body) {

        if (body == null || body.get("content") == null || body.get("content").isBlank()) {
            return ResponseEntity.status(400).body(Map.of("error", "Content is required"));
        }

        return callService.addNote(callId, body.get("content"))
                .<ResponseEntity<?>>map(note -> ResponseEntity.status(201).body(note))
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error", "Call not found")));
    }
}