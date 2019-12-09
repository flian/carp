package org.lotus.carp.showcase.card.service.impl;

import com.alibaba.druid.util.StringUtils;
import org.lotus.carp.showcase.card.convter.CardConverter;
import org.lotus.carp.showcase.card.domain.Card;
import org.lotus.carp.showcase.card.enums.CardTypeEnum;
import org.lotus.carp.showcase.card.repository.CardRepository;
import org.lotus.carp.showcase.card.service.CardService;
import org.lotus.carp.showcase.card.vo.CardCreateDto;
import org.lotus.carp.showcase.card.vo.CardCriteria;
import org.lotus.carp.showcase.card.vo.CardResult;
import org.lotus.carp.showcase.card.vo.CardUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @author : Foy Lian
 *         Date: 7/31/2017
 *         Time: 2:48 PM
 */
@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardConverter cardConverter;

    @Override
    public List<CardResult> list(CardCriteria query) {
        return cardConverter.toList(cardRepository.findAll());
    }

    @Override
    public Page<CardResult> query(CardCriteria query, Pageable page) {
        Specification<Card> specification = (root,criteriaQuery,cb)->{
            List<Predicate> list = new ArrayList<>();

            if (!StringUtils.isEmpty(query.getCardId())) {
                list.add(cb.like(root.get("cardId").as(String.class), "%" + query.getCardId() + "%"));
            }

            if (!StringUtils.isEmpty(query.getIssueValue())) {
                list.add(cb.equal(root.get("issueValue").as(BigDecimal.class), new BigDecimal(query.getIssueValue())));
            }
            return cb.and(list.toArray(new Predicate[list.size()]));
        };
        return cardConverter.toPageList(cardRepository.findAll(specification,page));
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public CardResult save(CardCreateDto dto) {
        Card card = new Card();
        card.setCardId(generateCardId());
        card.setIssueValue(dto.getIssueValue());
        card.setBalanceValue(card.getIssueValue());
        card.setFrozenValue(BigDecimal.ZERO);
        card = cardRepository.save(card);
        return cardConverter.toResult(card);
    }

    @Override
    public CardResult get(String cardCode) {
        return cardConverter.toResult(cardRepository.findByCardId(cardCode));
    }

    @Override
    public CardResult getByType(CardTypeEnum type) {
        //TODO pending
        return null;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public CardResult update(CardUpdateDto updateDto) {
        Card card = cardRepository.findByCardId(updateDto.getCardId());
        card.setFrozenValue(updateDto.getFrozenValue());
        card.setBalanceValue(updateDto.getBalanceValue());
        return cardConverter.toResult(cardRepository.save(card));
    }

    private String generateCardId() {
        LocalDate now = LocalDate.now();
        String cardIdPrefix = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return String.format("%s%s", cardIdPrefix, random());
    }

    @Override
    public boolean deleteCardByIds(Long[] ids) {
        return cardRepository.deleteByIds(ids) > 0;
    }

    private String random() {
        return "" + (int) (Math.random() * 1000000000);
    }
}
