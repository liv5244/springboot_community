package com.itliv.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    //QUESTION_NOT_FOUND问题未找到的异常处理信息
    //PERMISSION_DENIED修改问题时，输入的地址得到的问题  不是本人所发布的
    QUESTION_NOT_FOUND(404, "找不到问题，问题不存在或已经删除。要不要换个试试？"),
    PERMISSION_DENIED(405, "您没有权限修改该问题,请核对是否是您发布的哦~"),
    USER_NOT_LOGIN(406,"用户未登录，请登录后重试~"),
    TARGET_PARAM_NOT_FOUND(404,"未选中任何问题不能进行回复"),
    //说明回复既不是对于问题的回复   也不是回复的评论，，，参数是错误的
    TYPE_PARAM_WRONG(404, "回复类型的请求参数出错，请核对后再次尝试~~"),
    COMMENT_NOT_FOUND(404, "您回复的问题不存在~可能被删除或者参数输入有误，请核对后再操作~");

    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

}
