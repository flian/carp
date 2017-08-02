package org.lotus.carp.base.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/2/2017
 * Time: 3:07 PM
 */
@Data
public class ResponseWrapper<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    private int procCode;
    private T payload;
    private StringBuffer message = new StringBuffer(100);
    private Exception exception;

    public ResponseWrapper execSuccess() {
        procCode = 200;
        return this;
    }

    public ResponseWrapper execSuccess(T data) {
        procCode = 200;
        payload = data;
        return this;
    }

    public ResponseWrapper execFailue() {
        procCode = 99999;
        return this;
    }

    public ResponseWrapper addMessage(String msg) {
        message.append(msg);
        return this;
    }

    public ResponseWrapper addException(Exception e) {
        exception = e;
        return this;
    }

    public static ResponseWrapper create() {
        return new ResponseWrapper();
    }
}
