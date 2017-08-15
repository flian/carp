package org.lotus.carp.web.controller.cards;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.showcase.card.service.CardService;
import org.lotus.carp.showcase.card.vo.CardDto;
import org.lotus.carp.showcase.card.vo.CardResult;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 4:36 PM
 */
@RestController
@RequestMapping("/cards")
public class CardsController extends AdminBaseController{
    @Autowired
    private CardService cardService;
    @GetMapping
    public ResponseWrapper<List<CardResult>> list() {
        return response().execSuccess(cardService.list(null));
    }
    @GetMapping("/{cardId}")
    public ResponseWrapper<CardResult> get(@PathVariable("cardId") String cardId){
        return response().execSuccess(cardService.get(cardId));
    }
    @PostMapping
    public ResponseWrapper<CardResult> saveOrUpdate(CardDto dto){
        return response().execSuccess(cardService.save(dto));
    }
}
