package org.lotus.carp.base.event;

import lombok.Data;

/**
 *  角色、资源等改变
 *
 * @author: Foy Lian
 * Date: 11/28/2017
 * Time: 11:05 AM
 */
@Data
public class SecurityResourceChangedEvent extends CarpEvent {
    public static final String DEFAULT_CODE = "RESOURCE_CHANGED_EVENT";
    public static final String DEFAULT_EVENT_MESSAGE = "security resource has been changed.";

    public SecurityResourceChangedEvent() {
        super(DEFAULT_CODE, "DEFAULT_EVENT_MESSAGE");
    }
}
