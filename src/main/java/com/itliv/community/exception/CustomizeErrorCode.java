package com.itliv.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    //QUESTION_NOT_FOUND问题未找到的异常处理信息
    //PERMISSION_DENIED修改问题时，输入的地址得到的问题  不是本人所发布的
    QUESTION_NOT_FOUND("找不到问题，问题不存在或已经删除。要不要换个试试？"),
    PERMISSION_DENIED("您没有权限修改该问题,请核对是否是您发布的哦~");


    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
