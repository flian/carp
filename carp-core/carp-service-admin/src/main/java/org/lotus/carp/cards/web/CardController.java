package org.lotus.carp.cards.web;

import com.google.common.base.Preconditions;
import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.base.web.BaseController;
import org.lotus.carp.showcase.card.service.CardService;
import org.lotus.carp.showcase.card.vo.CardCreateDto;
import org.lotus.carp.showcase.card.vo.CardCriteria;
import org.lotus.carp.showcase.card.vo.CardResult;
import org.lotus.carp.showcase.card.vo.CardUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created with IntelliJ IDEA.
 *
 * @author : Foy Lian
 * Date: 7/31/2017
 * Time: 4:36 PM
 */
@Controller
@RequestMapping("/cards")
public class CardController implements BaseController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public String listPage() {
        return "cards/list";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseWrapper<Page<CardResult>> list(String cardId,String issueValue,Pageable page) {
        CardCriteria query = new CardCriteria();
        query.setCardId(cardId);
        query.setIssueValue(issueValue);
        return response().execSuccess(cardService.query(query, page));
    }

    @GetMapping("/{cardId}")
    @ResponseBody
    public ResponseWrapper<CardResult> get(@PathVariable("cardId") String cardId) {
        return response().execSuccess(cardService.get(cardId));
    }

    @PostMapping
    @ResponseBody
    public ResponseWrapper<CardResult> save(@Valid @RequestBody CardCreateDto dto) {
        return response().execSuccess(cardService.save(dto));
    }

    @PutMapping
    @ResponseBody
    public ResponseWrapper<CardResult> update(@Valid @RequestBody CardUpdateDto dto) {
        CardResult card = cardService.get(dto.getCardId());
        Preconditions.checkArgument(dto.getFrozenValue().compareTo(card.getIssueValue()) <= 0, "冻结金额不能大于卡面值");
        Preconditions.checkArgument(dto.getBalanceValue().compareTo(card.getIssueValue()) <= 0, "卡余额不能大于卡面值");
        return response().execSuccess(cardService.update(dto));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseWrapper<Boolean> deleteCardsByIds(@RequestBody Long[] ids) {
        return response().execSuccess(cardService.deleteCardByIds(ids));
    }
}
