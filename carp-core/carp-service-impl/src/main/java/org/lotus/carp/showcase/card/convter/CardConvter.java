package org.lotus.carp.showcase.card.convter;

import org.lotus.carp.showcase.card.domain.Card;
import org.lotus.carp.showcase.card.vo.CardDto;
import org.lotus.carp.showcase.card.vo.CardResult;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 5:10 PM
 */
@Component
public class CardConvter implements Converter<Card, CardResult> {
    public List<CardResult> toList(Iterable<Card> cards) {
        List<CardResult> result = new ArrayList<>();
        cards.forEach(c -> {
            result.add(toResult(c));
        });
        return result;
    }

    public Page<CardResult> toPageList(Page<Card> cards) {
        return cards.map(this);
    }

    public CardResult toResult(Card card) {
        CardResult result = new CardResult();
        BeanUtils.copyProperties(card, result);
        return result;
    }

    public Card fromDto(CardDto dto) {
        Card card = new Card();
        BeanUtils.copyProperties(dto, card);
        return card;
    }

    @Override
    public CardResult convert(Card source) {
        return toResult(source);
    }
}
