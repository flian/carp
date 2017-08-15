package org.lotus.carp.showcase.card.service.impl;

import org.lotus.carp.showcase.card.convter.CardConvter;
import org.lotus.carp.showcase.card.enums.CardTypeEnum;
import org.lotus.carp.showcase.card.repository.CardRepository;
import org.lotus.carp.showcase.card.service.CardService;
import org.lotus.carp.showcase.card.vo.CardCriteria;
import org.lotus.carp.showcase.card.vo.CardDto;
import org.lotus.carp.showcase.card.vo.CardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private CardRepository cardRepository;
    @Autowired
    private CardConvter cardConvter;

    @Override
    public List<CardResult> list(CardCriteria query) {
        return cardConvter.toList(cardRepository.findAll());
    }

    @Override
    public Page<CardResult> query(CardCriteria query, Pageable page) {
        return cardConvter.toPageList(cardRepository.findAll(page));
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
    public CardResult getByType(CardTypeEnum type) {
        //TODO pending
        return null;
    }
}
