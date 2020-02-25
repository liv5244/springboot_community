package com.itliv.community.enums;

public enum  NotificationEnum {
    //
    REPLY_QUESTIONS(1,"回复了问题"),
    REPLY_COMEMNTS(2,"回复了评论");

    private int type;
    private String name;

    NotificationEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
