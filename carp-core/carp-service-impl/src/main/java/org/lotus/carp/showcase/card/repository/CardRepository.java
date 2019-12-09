package org.lotus.carp.showcase.card.repository;

import org.lotus.carp.showcase.card.domain.Card;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/31/2017
 * Time: 5:00 PM
 */
@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long>,JpaSpecificationExecutor {
    Card findByCardId(String code);

    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("delete from Card where  id in (:ids)")
    int deleteByIds(@Param("ids") Long[] ids);
}
