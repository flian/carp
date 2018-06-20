package org.lotus.carp.base.web;

import com.google.common.base.Joiner;
import org.lotus.carp.base.vo.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * admin后台通用基础controller
 * @author : Foy Lian
 * Date: 8/2/2017
 * Time: 3:03 PM
 */
public interface BaseController {
     Logger baseLogger = LoggerFactory.getLogger(BaseController.class);

    /**
     *  参数校验失败错误统一显示
     * @param e exception
     * @return message
     */
     @ExceptionHandler
     @ResponseBody
     default Object exceptionHandle(MethodArgumentNotValidException e){
         List<String> messages = new ArrayList();
         e.getBindingResult().getAllErrors().forEach( error -> messages.add(error.getDefaultMessage()));
         return response().execFailue().addMessage(Joiner.on("\n").join(messages));
     }


    /**
     *  global exception handler
     * @param e exception
     * @return  exception message
     */
    @ExceptionHandler
    @ResponseBody
    default  Object exceptionHandle(Exception e) {
        baseLogger.error("接口调用异常",e);
        if(null == e){
            return  response().addMessage("系统错误!").execFailue();
        }
        return response().execFailue().addMessage(e.getMessage());
    }

    /**
     *  common response object
     * @return response
     */
    default ResponseWrapper response() {
        return ResponseWrapper.create();
    }
}
