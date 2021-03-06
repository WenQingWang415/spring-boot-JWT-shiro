package com.demo.exception;

/**
 * ExceptionTypeEnum 枚举异常分类，将异常分类固化下来，防止开发人员思维发散。
 */
public enum CustomExceptionType {
    USER_INPUT_ERROR(400, "您输入的数据错误或您没有权限访问资源！"),
    SYSTEM_ERROR(500, "系统出现异常，请您稍后再试或联系管理员！"),
    OTHER_ERROR(999, "系统出现未知异常，请联系管理员！");
    private String desc;//异常类型中文描述

    private int code; //code

    //构造方法
    CustomExceptionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    //get set方法
    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
