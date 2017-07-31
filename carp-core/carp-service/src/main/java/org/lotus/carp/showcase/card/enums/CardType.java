package org.lotus.carp.showcase.card.enums;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 2:32 PM
 */
public enum CardType {
    INVALID_CARD("0000", "invalid card"),
    NORMAL_CARD("0001", "normal card");

    private String code;
    private String displayName;

     CardType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
    public static CardType parse(String code){
        return Arrays.stream(CardType.values()).filter( c -> c.code.equals(code)).findFirst().get();
    }
}
