package com.jiessie.test01.utils.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;


@Slf4j
@Configuration
public class Elasticsearch6Configuration implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {

    @Value("${spring.data.elasticsearch.host}")
    private String host;

    @Value("${spring.data.elasticsearch.port}")
    private String port;

    @Value("${spring.data.elasticsearch.userName}")
    private String userName;

    @Value("${spring.data.elasticsearch.passWord}")
    private String passWord;

    @Value("${spring.data.elasticsearch.connectTimeout}")
    private Integer connectTimeout; //毫秒

    @Value("${spring.data.elasticsearch.socketTimeOut}")
    private Integer socketTimeOut;  //毫秒

    @Value("${spring.data.elasticsearch.connectionRequestTimeOut}")
    private Integer connectionRequestTimeOut; //毫秒

    @Value("${spring.data.elasticsearch.maxConnectNum}")
    private Integer maxConnectNum; // 最大连接数

    @Value("${spring.data.elasticsearch.maxConnectPerRoute}")
    private Integer maxConnectPerRoute; // 最大路由连接数

    private RestHighLevelClient restHighLevelClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        restHighLevelClient = getClient();
    }

    private RestHighLevelClient getClient() {

        RestHighLevelClient restHighLevelClient = null;

        ArrayList<HttpHost> hostList = new ArrayList<>();
        String[] hostStrs = host.split(",");
        for (String host : hostStrs) {
            hostList.add(new HttpHost(host, Integer.valueOf(port), "http"));
        }

        try {
            RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
            builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                @Override
                public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                    builder.setConnectTimeout(connectTimeout);
                    builder.setSocketTimeout(socketTimeOut);
                    builder.setConnectionRequestTimeout(connectionRequestTimeOut);
                    return builder;
                }
            });
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(userName, passWord));
            builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                    httpAsyncClientBuilder.disableAuthCaching();
                    httpAsyncClientBuilder.setMaxConnTotal(maxConnectNum);
                    httpAsyncClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                    return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                }
            });
            restHighLevelClient = new RestHighLevelClient(builder);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return restHighLevelClient;
    }

    @Override
    public void destroy() throws Exception {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            log.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public RestHighLevelClient getObject() throws Exception {
        return restHighLevelClient;
    }

    @Override
    public Class<?> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
