package org.lotus.carp.customer.enums;

import lombok.Getter;

/**
 *  用户进来的渠道
 *
 * @author: Foy Lian
 * Date: 8/16/2018
 * Time: 6:00 PM
 */
public enum From {
    NORMAL("常规渠道"),WECHAT("微信oauth2");
    @Getter
    private String description;
    From(String desc){
        description = desc;
    }
}
