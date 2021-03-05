package com.example.forum.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("你找的问题不见了，换一个吧", 2001),
    TARGET_PARAM_NOT_FOUND("未选择任何问题进行评论，换一个吧", 2002),
    NOT_LOGIN("用户未登录，请先登录后重新尝试",2003),
    SYS_ERROR("服务器累坏了，要不过一会再来试试吧",2004),
    TYPE_PARAM_WRONG("评论类型错误或不存在",2005),
    COMMENT_NOT_FOUND("回复的评论已经不存在了，换一个姿势再来尝试吧",2006);
    private String message;
    private Integer code;

    CustomizeErrorCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
