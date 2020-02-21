package com.itliv.community.enums;

public enum CommentTypeEnum {
    //question 1   comment 2
    QUESTION(1),
    COMMENT(2);

    private Integer typeCode;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (type == value.getTypeCode()) {
                return true;
            }
        }
        return false;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    CommentTypeEnum(Integer typeCode) {
        this.typeCode = typeCode;
    }
}
