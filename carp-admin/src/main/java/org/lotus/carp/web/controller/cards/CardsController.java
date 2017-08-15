package org.lotus.carp.web.controller.cards;

import org.lotus.carp.base.vo.ResponseWrapper;
import org.lotus.carp.showcase.card.service.CardService;
import org.lotus.carp.showcase.card.vo.CardDto;
import org.lotus.carp.showcase.card.vo.CardResult;
import org.lotus.carp.web.controller.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 4:36 PM
 */
@Controller
@RequestMapping("/cards")
public class CardsController extends AdminBaseController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public String listPage() {
        return "cards/list";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseWrapper<Page<CardResult>> list(Pageable page) {
        return response().execSuccess(cardService.query(null, page));
    }

    @GetMapping("/{cardId}")
    @ResponseBody
    public ResponseWrapper<CardResult> get(@PathVariable("cardId") String cardId) {
        return response().execSuccess(cardService.get(cardId));
    }

    @PostMapping
    @ResponseBody
    public ResponseWrapper<CardResult> saveOrUpdate(CardDto dto) {
        return response().execSuccess(cardService.save(dto));
    }
}
