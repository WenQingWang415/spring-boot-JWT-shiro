package com.demo.exception;

import lombok.Data;

/**
 * 统一请求分装
 * @Data Lombok 是一個 Java library，可以透過簡單的注解省略 Java 的 code，像是 setter、getter、logger…等，目的在消除冗長的 code 和提高開發效率
 */

@Data
public class AjaxResponse {
    private boolean isok; //请求是否处理成功 true或者false
    private int code; //请求响应状态码 使用http状态码 比如：200请求成功，400用户输入错误导致的异常，500系统内部异常
    private String message;  //请求结果描述信息
    private Object data; //请求结果数据（通常用于查询操作）


    private AjaxResponse() {

    }
    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CustomException e) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if (e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()) {
            resultBean.setMessage(e.getMessage());
        } else if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            resultBean.setMessage(e.getMessage() + ",请将该异常信息发送给管理员!");
        } else {
            resultBean.setMessage("系统出现未知异常，请联系管理员!");
        }
        //TODO 这里最好将异常信息持久化
        return resultBean;
    }

    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CustomExceptionType customExceptionType,
                                     String errorMessage) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(customExceptionType.getCode());
        resultBean.setMessage(errorMessage);
        return resultBean;
    }

    //请求处理成功时的数据响应
    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    //请求处理成功，并响应结果数据
    public static AjaxResponse success(Object data) {
        AjaxResponse ajaxResponse = AjaxResponse.success();
        ajaxResponse.setData(data);
        return ajaxResponse;
    }
}
