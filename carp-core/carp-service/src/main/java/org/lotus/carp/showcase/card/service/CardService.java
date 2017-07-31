package org.lotus.carp.showcase.card.service;

import org.lotus.carp.showcase.card.enums.CardType;
import org.lotus.carp.showcase.card.vo.CardDto;
import org.lotus.carp.showcase.card.vo.CardQuery;
import org.lotus.carp.showcase.card.vo.CardResult;

import javax.smartcardio.Card;
import java.util.List;

/**
 * just a show case service
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 2:25 PM
 */
public interface CardService {
    public List<CardResult> list(CardQuery query);
    public CardResult save(CardDto Dto);
    public CardResult get(String cardCode);
    public CardResult getByType(CardType type);
}
