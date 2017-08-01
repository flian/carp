package org.lotus.carp.showcase.card.service.impl;

import org.lotus.carp.showcase.card.convter.CardConvter;
import org.lotus.carp.showcase.card.enums.CardType;
import org.lotus.carp.showcase.card.repository.CardRepository;
import org.lotus.carp.showcase.card.service.CardService;
import org.lotus.carp.showcase.card.vo.CardDto;
import org.lotus.carp.showcase.card.vo.CardCriteria;
import org.lotus.carp.showcase.card.vo.CardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    CardConvter cardConvter;

    @Override
    public List<CardResult> list(CardCriteria query) {
        return cardConvter.toList(cardRepository.findAll());
    }

    @Override
    public CardResult save(CardDto dto) {
        return cardConvter.toResult(cardRepository.save(cardConvter.fromDto(dto)));
    }

    @Override
    public CardResult get(String cardCode) {
        return cardConvter.toResult(cardRepository.findByCardId(cardCode));
    }

    @Override
    public CardResult getByType(CardType type) {
        //TODO pending
        return null;
    }
}
