package com.spring.chatapp.websocket;

import java.time.LocalDateTime;

public class OutputMessage {

    private String from;
    private String text;
    private LocalDateTime time;

    public OutputMessage(String from, String text, LocalDateTime time) {
        this.from = from;
        this.text = text;
        this.time = time;
    }

    // Getters and setters
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

