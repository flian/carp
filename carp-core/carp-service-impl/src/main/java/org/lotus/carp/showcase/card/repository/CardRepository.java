package org.lotus.carp.showcase.card.repository;

import org.lotus.carp.showcase.card.domain.Card;
import org.springframework.data.repository.CrudRepository;


/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 5:00 PM
 */
public interface CardRepository extends CrudRepository<Card, Long> {
    Card findByCardId(String code);
}
