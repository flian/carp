package org.lotus.carp.showcase.card.service.impl;

import org.lotus.carp.showcase.card.enums.CardType;
import org.lotus.carp.showcase.card.service.CardService;
import org.lotus.carp.showcase.card.vo.CardQuery;
import org.lotus.carp.showcase.card.vo.CardResult;

import javax.smartcardio.Card;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 2:48 PM
 */
public class CardServiceImpl implements CardService {
    @Override
    public List<CardResult> list(CardQuery query) {
        return null;
    }

    @Override
    public CardResult save(Card Dto) {
        return null;
    }

    @Override
    public CardResult get(String cardCode) {
        return null;
    }

    @Override
    public CardResult getByType(CardType type) {
        return null;
    }
}
