package com.aircall.dashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Note {

	private String id;
	@JsonIgnore
	private String callId;
	private String content;
	
	public Note() {}
	
	public Note(String id,String callId,String content) {
		this.id = id;
		this.callId = callId;
		this.content = content;
		}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", callId='" + callId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
