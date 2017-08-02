package org.lotus.carp.base.utils.excel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        Map settlement1 = new HashMap();
        settlement1.put("orerCode", "001");
        settlement1.put("sapOrderCode", "aa");
        settlement1.put("pointPrice", "11");
        settlement1.put("totalAmount", "11");
        settlement1.put("actualAmount", "122");
        settlement1.put("productName", "aaa");

        Map settlement2 = new HashMap();
        settlement2.put("orerCode", "002");

        list.add(settlement1);
        list.add(settlement2);

        params.put("settlementDetailList", list);

        params.put("settlement", genSettlementDetail());

        out = new ByteArrayOutputStream();
    }

    @Test
    public void testProcessTemplate() throws Exception {
        JxlsTemplate.processTemplate("test.xls", out, params);
        Assert.assertTrue("should render some message.", out.size() > 0);
    }

    @Test
    public void testProcessTemplate1() throws Exception {
        JxlsTemplate.processTemplate(JxlsTemplateTest.class, "test.xls", out, params);
        Assert.assertTrue("should render some message.", out.size() > 0);
    }

    @Test
    public void testProcessTemplate2() throws Exception {
        Class clazz = JxlsTemplateTest.class;
        String file = "test.xls";
        JxlsTemplate.processTemplate(clazz, file, out, params);
        Assert.assertTrue("should render some message.", out.size() > 0);
    }

    @Test
    public void testProcessTemplateWithInvalidFile() {
        Class clazz = JxlsTemplateTest.class;
        String file = "NO_EXIST.xls";
        try {
            JxlsTemplate.processTemplate(clazz, file, out, params);
            Assert.fail("Excepted an TemplateNotFoundException to be thrown.");
        } catch (TemplateNotFoundException e) {
        } catch (IOException e) {
            Assert.fail("Excepted an TemplateNotFoundException to be thrown.");
        }
    }

    private Map genSettlementDetail() {
        Map settlement = new HashMap();
        settlement.put("balanceCode", "SC0001");
        settlement.put("status", "已结算");
        settlement.put("supplierName", "SAP");
        settlement.put("balanceStartTime", new Date());
        settlement.put("balanceEndTime", new Date());
        settlement.put("cashPrice", "12.00");
        settlement.put("platformRate", "1");
        settlement.put("platformCost", "5");
        settlement.put("bankAccountName", "6224");
        settlement.put("bankAccountCard", "中国银行");
        return settlement;
    }
}