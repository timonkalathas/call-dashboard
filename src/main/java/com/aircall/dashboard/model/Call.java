package com.aircall.dashboard.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Call {
    private String id;
    private String direction;
    private String from;
    private String to;
    private String callType;
    private int duration;
    private boolean isArchived;
    private Instant createdAt;
    private List<Note> notes = new ArrayList<>();


    public Call() {
    }


    public Call(String id, String direction, String from, String to, String callType, int duration, boolean isArchived, Instant createdAt) {
        this.id = id;
        this.direction = direction;
        this.from = from;
        this.to = to;
        this.callType = callType;
        this.duration = duration;
        this.isArchived = isArchived;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    @Override
    public String toString() {
        return "Call{" +
                "id='" + id + '\'' +
                ", direction='" + direction + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", callType='" + callType + '\'' +
                ", duration=" + duration + "s" +
                ", isArchived=" + isArchived +
                ", createdAt=" + createdAt +
                ", notesCount=" + notes.size() +
                '}';
    }
}