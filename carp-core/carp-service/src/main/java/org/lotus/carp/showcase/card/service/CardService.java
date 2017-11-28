package org.lotus.carp.showcase.card.service;

import org.lotus.carp.showcase.card.enums.CardTypeEnum;
import org.lotus.carp.showcase.card.vo.CardCriteria;
import org.lotus.carp.showcase.card.vo.CardCreateDto;
import org.lotus.carp.showcase.card.vo.CardResult;
import org.lotus.carp.showcase.card.vo.CardUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * just a show case service
 * @author : Foy Lian
 * Date: 7/31/2017
 * Time: 2:25 PM
 */
public interface CardService {
    public List<CardResult> list(CardCriteria query);
    public Page<CardResult> query(CardCriteria query, Pageable page);
    public CardResult save(CardCreateDto createDto);
    public CardResult update(CardUpdateDto updateDto);
    public CardResult get(String cardCode);
    public CardResult getByType(CardTypeEnum type);
    public boolean deleteCardByIds(Long [] ids);
}
