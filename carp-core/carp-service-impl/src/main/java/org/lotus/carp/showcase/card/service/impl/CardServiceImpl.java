package org.lotus.carp.showcase.card.service.impl;

import org.lotus.carp.showcase.card.enums.CardType;
import org.lotus.carp.showcase.card.repository.CardRepository;
import org.lotus.carp.showcase.card.service.CardService;
import org.lotus.carp.showcase.card.vo.CardQuery;
import org.lotus.carp.showcase.card.vo.CardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 2:48 PM
 */
@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

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
