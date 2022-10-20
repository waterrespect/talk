package com.wang.blog.enums;

public enum ArticleStatusEnum {
    DELETED_FALSE(0, "未删除"),
    DELETED_TRUE(1, "已删除"),

    PUBLIC_STATUS(1, "公开"),
    PRIVATE_STATUS(2, "私密"),
    PUBLIC_COMMENT_STATUS(3, "评论可见"),
    ;

    private Integer status;
    private String name;

    ArticleStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public static String getStatusNameByStatus(Integer status) {
        AuthStatusEnum arrObj[] = AuthStatusEnum.values();
        for (AuthStatusEnum obj : arrObj) {
            if (status.intValue() == obj.getStatus().intValue()) {
                return obj.getName();
            }
        }
        return "";
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
