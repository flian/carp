package org.lotus.carp.commerce.product.converter;
import org.lotus.carp.commerce.product.domain.Product;
import org.lotus.carp.commerce.product.vo.ProductResult;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 4:43 PM
 */
@Component
public class ProductConverter implements  Converter<Product,ProductResult> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public ProductResult convert(Product source) {
        ProductResult result = new ProductResult();
        BeanUtils.copyProperties(source,result);
        return result;
    }
}
