package org.lotus.carp.base.utils.excel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * JXlS 模板处理方法
 * User: Foy Lian
 * Date: 7/28/2017
 * Time: 3:03 PM
 */
public class JxlsTemplate {
    /**
     * 模板路径
     */
    private static final String TEMPLATE_DIR = "/excelTemplates/";

    protected static Log logger = LogFactory.getLog(JxlsTemplate.class);

    /**
     *
     * @param templateStream excel模板流
     * @param out 生成excel写入的输出流
     * @param context jxsl上下文
     * @throws IOException
     */
    private static void processTemplate(InputStream templateStream, OutputStream out, Context context) throws IOException {
        JxlsHelper.getInstance().processTemplate(templateStream, out, context);
    }

    /**
     *
     * @param templateStream excel模板流
     * @param out  生成excel写入的输出流
     * @param params  交给jxls处理模板需要的参数
     * @throws IOException
     */
    public static void processTemplate(InputStream templateStream, OutputStream out, Map<String, ?> params) throws IOException {
        Context context = new Context();
        if (params != null) {
            for (String key : params.keySet()) {
                context.putVar(key, params.get(key));
            }
        }
        processTemplate(templateStream, out, context);
    }

    /**
     *  使用JxlsTemplate.class.getResourceAsStream load 模板
     * @param template 模板名称，相当于TEMPLATE_DIR设置的路径
     * @param out  生成excel写入的输出流
     * @param params 交给jxls处理模板需要的参数
     * @throws IOException
     */
    public static void processTemplate(String template, OutputStream out, Map<String, ?> params) throws IOException {
        processTemplate(JxlsTemplate.class, template, out, params);
    }

    /**
     * 使用resourceBaseClassgetResourceAsStream load 模板
     * @param resourceBaseClass  class load的类
     * @param template  模板名称
     * @param out  生成excel写入的输出流
     * @param params  交给jxls处理模板需要的参数
     * @throws IOException
     */
    public static void processTemplate(Class resourceBaseClass, String template, OutputStream out, Map<String, ?> params) throws IOException {
        InputStream in = resourceBaseClass.getResourceAsStream(TEMPLATE_DIR + template);
        if (null == in) {
            logger.error("can't find excel template by path:" + TEMPLATE_DIR + template);
            throw new RuntimeException("找不到excel模板！,位置:" + TEMPLATE_DIR + template);
        }
        processTemplate(in, out, params);
    }
}
