package com.pragma.mensajeria.domain.model;

public class Message {

    private String destination;
    private String text;

    public Message() {
    }

    public Message(String destination, String text) {
        this.destination = destination;
        this.text = text;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}