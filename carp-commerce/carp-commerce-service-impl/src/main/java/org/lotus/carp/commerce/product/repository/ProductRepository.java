package org.lotus.carp.commerce.product.repository;

import org.lotus.carp.commerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 4:42 PM
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
}
