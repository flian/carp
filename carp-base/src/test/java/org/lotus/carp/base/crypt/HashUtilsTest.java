package org.lotus.carp.base.crypt;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 2/26/2019
 * Time: 10:44 AM
 */
public class HashUtilsTest {

    @Test
    public void sha256() {
        String exceptedSha256Hash = "686609da0299bee56871ef764fdaabfb518c52d2a5df47037b4249cfcfcda7a7";
        InputStream in = HashUtilsTest.class.getClassLoader().getResourceAsStream("images/1.jpg");
        long t1 = System.currentTimeMillis();
        String sha256 = HashUtils.sha256(in);
        long t2 = System.currentTimeMillis();
        System.out.println("hash time:"+(t2-t1) +"ms");
        Assert.assertEquals(exceptedSha256Hash, sha256);
    }

    @Test
    public void sha256String(){
        String text="hellofoyhowareu?";
        String exceptedHash = "75e5b3dba5b92d45bff9009b2deb155c4e38d2ed53230d64e5dc634b10533e2d";
        long t1 = System.currentTimeMillis();
        String sha256 = HashUtils.sha256String(text);
        long t2 = System.currentTimeMillis();
        System.out.println("hash time:"+(t2-t1) +"ms");
        Assert.assertEquals(exceptedHash, sha256);
    }
}