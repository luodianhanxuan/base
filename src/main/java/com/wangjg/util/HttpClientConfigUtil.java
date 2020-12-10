package com.wangjg.util;

import org.apache.http.HttpHost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * @author wangjg
 * 2020/12/10
 */
@SuppressWarnings("unused")
public class HttpClientConfigUtil {

    private HttpClientBuilder httpClientBuilder;

    public static HttpClientConfigUtil init() {
        return init(null);
    }

    public static HttpClientConfigUtil init(HttpClientBuilder httpClientBuilder) {
        HttpClientConfigUtil httpClientConfigUtil = new HttpClientConfigUtil();
        if (httpClientBuilder != null) {
            httpClientConfigUtil.httpClientBuilder = httpClientBuilder;
        } else {
            httpClientConfigUtil.httpClientBuilder = HttpClients.custom();
        }
        return httpClientConfigUtil;
    }

    public CloseableHttpClient build() {
        return httpClientBuilder.build();
    }

    public HttpClientConfigUtil proxy(String hostOrIp, int port) {
        HttpHost proxy = new HttpHost(hostOrIp, port);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        httpClientBuilder.setRoutePlanner(routePlanner);
        return this;
    }

    public HttpClientConfigUtil sslIgnore() {
        // 采用绕过验证的方式处理Https请求
        SSLContext sslContext = null;
        try {
            sslContext = createIgnoreVerifySSL();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 依次是代理地址，代理端口，协议类型
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(Objects.requireNonNull(sslContext)))
                .build();
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        httpClientBuilder.setConnectionManager(clientConnectionManager);
        return this;
    }

    private SSLContext createIgnoreVerifySSL() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sc = SSLContext.getInstance("SSLv3");
        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new TrustManager[]{x509TrustManager}, null);
        return sc;
    }


}
