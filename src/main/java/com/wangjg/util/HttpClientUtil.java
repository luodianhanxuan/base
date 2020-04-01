package com.wangjg.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author wangjg
 * 2020/4/1
 */
public class HttpClientUtil {

    private static <ReturnType> ReturnType execute(HttpRequestBase httpRequestBase
            , Supplier<RequestConfig> requestConfigSupplier
            , Function<CloseableHttpResponse, ReturnType> parser) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = requestConfigSupplier.get();
            // 将上面的配置信息 运用到这个Get请求里
            httpRequestBase.setConfig(requestConfig);
            // 由客户端执行(发送) Get请求
            response = httpClient.execute(httpRequestBase);
            return parser.apply(response);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException ignored) {
            }
        }
        return null;
    }

    public static String get() {
        // 参数
        URI uri;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("name", "&"));
            params.add(new BasicNameValuePair("age", "18"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost("localhost")
                    .setPort(12345).setPath("/doGetControllerTwo")
                    .setParameters(params).build();
            HttpGet httpGet = new HttpGet(uri);

            return execute(httpGet, HttpClientUtil::getRequestConfig, response -> {
                HttpEntity entity = response.getEntity();
                try {
                    return EntityUtils.toString(entity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            });
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    private static RequestConfig getRequestConfig() {
        return RequestConfig.custom()
                // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(5000)
                // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(5000)
                // socket读写超时时间(单位毫秒)
                .setSocketTimeout(5000)
                // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(true).build();
    }
}
