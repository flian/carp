package org.lotus.carp.base.config.logging;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Rest Template logging class
 *
 * @author: Foy Lian
 * Date: 6/26/2018
 * Time: 9:52 AM
 */
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {
    final static Logger log = LoggerFactory.getLogger(LoggingRequestInterceptor.class);
    /**
     * print log use System.out.println
     */
    @Getter
    @Setter
    private boolean printConsole = false;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        if (log.isDebugEnabled() || printConsole) {
            printLog("===========================request begin================================================");
            printLog("URI         : {}", request.getURI());
            printLog("Method      : {}", request.getMethod());
            printLog("Headers     : {}", request.getHeaders());
            printLog("Request body: {}", new String(body, "UTF-8"));
            printLog("==========================request end================================================");
        }
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        if (log.isDebugEnabled() || printConsole) {
            StringBuilder inputStringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }
            printLog("============================response begin==========================================");
            printLog("Status code  : {}", response.getStatusCode());
            printLog("Status text  : {}", response.getStatusText());
            printLog("Headers      : {}", response.getHeaders());
            printLog("Response body: {}", inputStringBuilder.toString());
            printLog("=======================response end=================================================");
        }

    }

    private void printLog(String s) {
        printLog(s, "");
    }

    private void printLog(String s, Object o) {
        if (printConsole) {
            System.out.println(s + o);
        }
        log.debug(s, o);
    }
}
