package com.aircall.dashboard.service;

import com.aircall.dashboard.model.Call;
import com.aircall.dashboard.repository.CallRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
}