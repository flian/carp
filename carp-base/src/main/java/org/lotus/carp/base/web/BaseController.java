package org.lotus.carp.base.web;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/2/2017
 * Time: 3:03 PM
 */
public interface BaseController {
    @ExceptionHandler
    @ResponseBody
    default public Object exceptionHandle(Exception e) {
        return e == null ? this.response().addMessage("系统错误!").execFailue() : (e.getMessage() == null ? this.response().addException(e).addMessage("系统错误!").execFailue() : this.response().addException(e).addMessage(e.getMessage()).execFailue());
    }

    default ResponseWrapper response() {
        return ResponseWrapper.create();
    }
}
