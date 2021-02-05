package com.example.forum.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOTFOUND("你找的问题不见了，换一个吧");
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        message = message;
    }
}
