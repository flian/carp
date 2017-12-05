package org.lotus.carp.commerce.customer.converter;

import org.lotus.carp.commerce.customer.domain.Customer;
import org.lotus.carp.commerce.customer.vo.CustomerResult;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 4:31 PM
 */
@Component
public class CustomerConverter implements Converter<Customer, CustomerResult> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public CustomerResult convert(Customer source) {
        CustomerResult result = new CustomerResult();
        BeanUtils.copyProperties(source, result);
        return result;
    }
}
