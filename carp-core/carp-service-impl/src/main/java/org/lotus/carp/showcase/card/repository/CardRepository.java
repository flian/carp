package org.lotus.carp.showcase.card.repository;

import org.lotus.carp.showcase.card.domain.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 5:00 PM
 */
@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {
    Card findByCardId(String code);
}
