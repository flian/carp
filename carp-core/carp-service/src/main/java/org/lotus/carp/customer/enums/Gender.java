package org.lotus.carp.customer.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 10:29 AM
 */
public enum Gender {
    UNKNOWN(-1, "unknown", "未知"),
    MALE(1, "male", "男"),
    FEMALE(2, "female", "女");

    @Getter
    private Integer code;
    @Getter
    private String name;
    @Getter
    private String description;

    Gender(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public static Gender parse(Integer code) {
        for (Gender g : Gender.values()) {
            if (code.equals(g.code)) {
                return g;
            }
        }
        return UNKNOWN;
    }

}
