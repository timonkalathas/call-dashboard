package com.aircall.dashboard.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aircall.dashboard.model.Call;
import com.aircall.dashboard.model.Note;
import com.aircall.dashboard.repository.CallRepository;

@Service
public class CallService {

  
    private final CallRepository callRepository;

    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

  
    public List<Call> getAllCalls() {
        return callRepository.findAll().stream()
                .filter(call -> !call.isArchived())
                .collect(Collectors.toList());
    }

  
  
    public Optional<Call> getCallById(String id) {
        return callRepository.findById(id);
    }

    public Optional<Call> archiveCall(String id) {
        Optional<Call> optionalCall = callRepository.findById(id);

        if (optionalCall.isPresent()) {
            Call call = optionalCall.get();
            call.setArchived(true);
            callRepository.save(call);
            return Optional.of(call);
        }

        return Optional.empty();
    }
    public Optional<Note> addNote(String callId, String content) {
        Optional<Call> optionalCall = callRepository.findById(callId);

        if (optionalCall.isPresent()) {
            Call call = optionalCall.get();
            String noteId = "note-" + UUID.randomUUID().toString().substring(0, 6);
            Note newNote = new Note(noteId, callId, content);
            
            call.getNotes().add(newNote);
            callRepository.save(call);
            
            return Optional.of(newNote);
        }

        return Optional.empty();
    }

}