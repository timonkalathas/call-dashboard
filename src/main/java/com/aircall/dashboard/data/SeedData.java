package com.aircall.dashboard.data;

import com.aircall.dashboard.model.Call;
import com.aircall.dashboard.model.Note;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SeedData {

    public static List<Call> getInitialCalls() {
        List<Call> calls = new ArrayList<>();

        // Call 1 - Answered Inbound
        calls.add(new Call("1", "inbound", "+33 6 12 34 56 78", "+33 1 23 45 67 89",
                "answered", 120, false, Instant.parse("2025-04-10T14:32:00Z")));

        // Call 2 - Missed Outbound
        calls.add(new Call("2", "outbound", "+33 1 23 45 67 89", "+33 6 98 76 54 32",
                "missed", 0, false, Instant.parse("2025-04-10T15:10:00Z")));

        // Call 3 - Voicemail Inbound (Archived) με Note
        Call call3 = new Call("3", "inbound", "+33 7 55 44 33 22", "+33 1 23 45 67 89",
                "voicemail", 45, true, Instant.parse("2025-04-09T09:15:00Z"));
        call3.addNote(new Note("note-1", "3", "Customer left a message about their invoice"));
        calls.add(call3);

        // Call 4 - Answered Outbound
        calls.add(new Call("4", "outbound", "+33 1 23 45 67 89", "+33 6 11 22 33 44",
                "answered", 300, false, Instant.parse("2025-04-09T14:45:00Z")));

        // Call 5 - Missed Inbound
        calls.add(new Call("5", "inbound", "+33 6 55 66 77 88", "+33 1 23 45 67 89",
                "missed", 0, false, Instant.parse("2025-04-08T11:20:00Z")));

        // Call 6 - Answered Inbound (Archived)
        calls.add(new Call("6", "inbound", "+33 6 44 55 66 77", "+33 1 23 45 67 89",
                "answered", 60, true, Instant.parse("2025-04-08T13:05:00Z")));

        // Call 7 - Answered Inbound με Note
        Call call7 = new Call("7", "inbound", "+33 6 22 33 44 55", "+33 1 23 45 67 89",
                "answered", 540, false, Instant.parse("2025-04-07T17:00:00Z"));
        call7.addNote(new Note("note-2", "7", "Technical support resolved WiFi connectivity issue"));
        calls.add(call7);

        // Call 8 - Voicemail Outbound
        calls.add(new Call("8", "outbound", "+33 1 23 45 67 89", "+33 7 55 44 33 22",
                "voicemail", 30, false, Instant.parse("2025-04-07T11:30:00Z")));

        // Call 9 - Missed Outbound
        calls.add(new Call("9", "outbound", "+33 1 23 45 67 89", "+33 6 99 88 77 66",
                "missed", 0, false, Instant.parse("2025-04-06T08:45:00Z")));

        // Call 10 - Answered Inbound
        calls.add(new Call("10", "inbound", "+33 6 33 22 11 00", "+33 1 23 45 67 89",
                "answered", 180, false, Instant.parse("2025-04-06T12:30:00Z")));

        return calls;
    }
}