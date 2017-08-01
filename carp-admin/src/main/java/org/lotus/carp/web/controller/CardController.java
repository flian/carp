package org.lotus.carp.web.controller;

import org.lotus.carp.showcase.card.service.CardService;
import org.lotus.carp.showcase.card.vo.CardDto;
import org.lotus.carp.showcase.card.vo.CardResult;
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
@RequestMapping("/admin/cards")
public class CardController {
    @Autowired
    private CardService cardService;
    @GetMapping
    public List<CardResult> list() {
        return cardService.list(null);
    }
    @GetMapping("/{cardId}")
    public CardResult get(@PathVariable("cardId") String cardId){
        return cardService.get(cardId);
    }
    @PostMapping
    public CardResult saveOrUpdate(CardDto dto){
        return cardService.save(dto);
    }
}
