package org.lotus.carp.commerce.customer.domain;

import org.lotus.carp.commerce.customer.enums.Gender;

import javax.persistence.AttributeConverter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 2:56 PM
 */
public class GenderConverter implements AttributeConverter<Gender, Integer> {
    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database column
     */
    @Override
    public Integer convertToDatabaseColumn(Gender attribute) {
        if (null == attribute) {
            return Gender.UNKNOWN.getCode();
        }
        return attribute.getCode();
    }

    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct dbData type for the corresponding column
     * for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param dbData the data from the database column to be converted
     * @return the converted value to be stored in the entity attribute
     */
    @Override
    public Gender convertToEntityAttribute(Integer dbData) {
        for (Gender g : Gender.values()) {
            if (g.getCode().equals(dbData)) {
                return g;
            }
        }
        return Gender.UNKNOWN;
    }
}
