package org.lotus.carp.base.utils.excel;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/2/2017
 * Time: 9:53 AM
 */
public class TemplateNotFoundException extends RuntimeException {
    public TemplateNotFoundException() {
        super();
    }

    public TemplateNotFoundException(String message) {
        super(message);
    }
}
