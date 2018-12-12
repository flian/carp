package org.lotus.carp.base.crypt;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/12/2018
 * Time: 12:40 PM
 */
public class AesCryptUtilsTest {

    @Test
    public void encryptTest() {
        String key = "password";
        String plainText = "hello AesCryptUtilsTest, it should work";
        String encode = AesCryptUtils.encrypt(plainText,key);
        Assert.assertEquals(plainText, AesCryptUtils.decrypt(encode,key));
    }
    @Test
    public void testLongKey() {
        String plainText = "hello AesCryptUtilsTest, it should work";
        String key="sadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkf'sadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfsadfasdfasdmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdasdkfasdlkmaskfmdafsdfasfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff";
        String encode = AesCryptUtils.encrypt(plainText,key);
        Assert.assertEquals(plainText, AesCryptUtils.decrypt(encode,key));
    }
}