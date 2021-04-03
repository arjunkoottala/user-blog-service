package com.asajayan.user.domain.utils;

public enum SubscripionResponses {
    SUBSCRIBED("Successfully Subscribed"),
    UNSUBSCRIBED("Successfully Unsubscribed");

    private final String message;

    SubscripionResponses(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
