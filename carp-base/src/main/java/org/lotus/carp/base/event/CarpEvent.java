package org.lotus.carp.base.event;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/28/2017
 * Time: 11:02 AM
 */
@Data
public class CarpEvent implements Serializable {

    private static final long serialVersionUID = -4618617841701921593L;
    protected String code;
    protected String eventMessage;

    public CarpEvent(String code, String eventMessage) {
        this.code = code;
        this.eventMessage = eventMessage;
    }

}
