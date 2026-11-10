package com.aircall.dashboard;

import com.aircall.dashboard.model.Call;
import com.aircall.dashboard.repository.CallRepository;
import com.aircall.dashboard.service.CallService;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
      
        CallRepository callRepository = new CallRepository();
        CallService callService = new CallService(callRepository);

        System.out.println("=== 1. TEST: GET ALL ACTIVE CALLS (EXCLUDING ARCHIVED) ===");
        List<Call> activeCalls = callService.getAllCalls();
        for (Call call : activeCalls) {
            System.out.println(call);
        }
        System.out.println("Total active calls: " + activeCalls.size());

        System.out.println("\n=== 2. TEST: GET CALL BY ID (WITH NOTES) ===");
    
        Optional<Call> callWithNotes = callService.getCallById("3");
        if (callWithNotes.isPresent()) {
            Call c = callWithNotes.get();
            System.out.println("Found call: " + c);
            System.out.println("Notes: " + c.getNotes());
        } else {
            System.out.println("Call not found!");
        }

      
        Optional<Call> notFoundCall = callService.getCallById("999");
        System.out.println("Search for ID 999 is present: " + notFoundCall.isPresent());

        System.out.println("\n=== 3. TEST: ARCHIVE A CALL ===");
      
        System.out.println("Archiving call with ID 1...");
        Optional<Call> archivedCall = callService.archiveCall("1");
        archivedCall.ifPresent(call -> System.out.println("Updated call status: " + call));

        
        List<Call> updatedActiveCalls = callService.getAllCalls();
        System.out.println("Total active calls after archiving: " + updatedActiveCalls.size());
    }
}