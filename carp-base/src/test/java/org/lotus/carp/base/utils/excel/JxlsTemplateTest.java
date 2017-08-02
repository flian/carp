package org.lotus.carp.base.utils.excel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 7/28/2017
 * Time: 3:38 PM
 */

public class JxlsTemplateTest {
    private Map params;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() throws Exception {
        params = new HashMap();
        List list = new ArrayList();
        Map item1 = new HashMap();
        item1.put("orerCode", "001");
        item1.put("sapOrderCode", "aa");
        item1.put("pointPrice", "11");
        item1.put("totalAmount", "11");
        item1.put("actualAmount", "122");
        item1.put("productName", "aaa");

        Map item2 = new HashMap();
        item1.put("orerCode", "002");
        list.add(item1);
        list.add(item2);
        params.put("settlementDetailList", list);
        params.put("settlement", new HashMap() {{
            put("balanceCode", "SC0001");
            put("status", "已结算");
            put("supplierName", "SAP");
            put("balanceStartTime", new Date());
            put("balanceEndTime", new Date());
            put("cashPrice", "12.00");
            put("platformRate", "1");
            put("platformCost", "5");
            put("bankAccountName", "6224");
            put("bankAccountCard", "中国银行");
        }});
        out = new ByteArrayOutputStream();
    }

    @Test
    public void testProcessTemplate() throws Exception {
        JxlsTemplate.processTemplate("test.xls", out, params);
        Assert.assertTrue("should render some message.",out.size()>0);
    }

    @Test
    public void testProcessTemplate1() throws Exception {
        JxlsTemplate.processTemplate(JxlsTemplateTest.class, "test.xls", out, params);
        Assert.assertTrue("should render some message.",out.size()>0);
    }

}