package com.demo.exception;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/***
 * AjaxResponse的code是400代表的是业务状态，也就是说用户的请求业务失败了
 * 但是HTTP请求是成功的，也就是说数据是正常返回的。
 * 公司开发RESTful服务时，要求HTTP状态码能够体现业务的最终执行状态，所以说：我们有必要让业务状态与HTTP协议Response状态码一致。
 * 因为在GlobalResponseAdvice 里面会统一再封装为AjaxResponse。
 */
@Component
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }
  //进行统一封装，不在在Controller 统一返回AjaxResponse结果 做到返回什么就是什么
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //如果响应结果是JSON数据类型
        if(mediaType.equalsTypeAndSubtype(
                MediaType.APPLICATION_JSON)){
            if(body instanceof AjaxResponse){
                AjaxResponse ajaxResponse = (AjaxResponse)body;
                if(ajaxResponse.getCode() != 999){ //999 不是标准的HTTP状态码，特殊处理
                    serverHttpResponse.setStatusCode(HttpStatus.valueOf(
                            ajaxResponse.getCode()
                    ));
                }

                return body;
            }else{
                serverHttpResponse.setStatusCode(HttpStatus.OK);
                return AjaxResponse.success(body);
            }

        }


        return body;
    }
}
