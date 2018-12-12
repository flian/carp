package org.lotus.carp.base.crypt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/12/2018
 * Time: 4:28 PM
 */
public class RandomUtilsTest {

    @Test
    public void randomText() {
        for(int i=0;i<10;i++){
            System.out.println(RandomUtils.randomText(20));
        }
    }
}