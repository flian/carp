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
 * Created with IntelliJ IDEA.
 * @author : Foy Lian
 * Date: 8/2/2017
 * Time: 3:03 PM
 */
public interface BaseController {
     Logger baseLogger = LoggerFactory.getLogger(BaseController.class);
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
        //参数验证错误
        if( e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e ;
            List<String> messages = new ArrayList();
            me.getBindingResult().getAllErrors().forEach( error -> messages.add(error.getDefaultMessage()));
            return response().execFailue().addMessage(Joiner.on("\n").join(messages));
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
