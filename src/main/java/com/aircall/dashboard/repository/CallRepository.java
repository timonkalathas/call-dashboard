package com.aircall.dashboard.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.aircall.dashboard.data.SeedData;
import com.aircall.dashboard.model.Call;

 @Repository
public class CallRepository {

   
    private final List<Call> calls;

    
    public CallRepository() {
        this.calls = new ArrayList<>(SeedData.getInitialCalls());
    }

    
    public List<Call> findAll() {
        return new ArrayList<>(calls);
    }

  
    public Optional<Call> findById(String id) {
        return calls.stream()
                .filter(call -> call.getId().equals(id))
                .findFirst();
    }

  
    public void save(Call updatedCall) {
        for (int i = 0; i < calls.size(); i++) {
            if (calls.get(i).getId().equals(updatedCall.getId())) {
                calls.set(i, updatedCall);
                return;
            }
        }
        calls.add(updatedCall);
    }
}