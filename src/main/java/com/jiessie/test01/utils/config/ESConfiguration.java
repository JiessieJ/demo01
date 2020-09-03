/*
package com.jiessie.test01.utils.config;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ESConfiguration implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {

    private Logger logger = LoggerFactory.getLogger(ESConfiguration.class);

    private String url;

    private String userName;

    private String passWord;

    private Integer connectTimeout; //毫秒

    private Integer socketTimeOut;  //毫秒

    private Integer connectionRequestTimeOut; //毫秒

    private RestHighLevelClient restHighLevelClient;

    private static final String schema = "http";

    @Override
    public void afterPropertiesSet() throws Exception {
        restHighLevelClient = buildClient();
    }

    private RestHighLevelClient buildClient() {
        try {
            if(!url.contains(":")){
                logger.info("restHighLevelClient为null，url为"+url);
                return null;
            }
            RestClientBuilder builder = RestClient.builder(new HttpHost(url.split(":")[0], Integer.parseInt(url.split(":")[1]), schema));
            builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback(){
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
            builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback(){
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                    httpAsyncClientBuilder.disableAuthCaching();
                    return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                }
            });
            restHighLevelClient = new RestHighLevelClient(builder);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return restHighLevelClient;
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

    @Override
    public void destroy() throws Exception {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getSocketTimeOut() {
        return socketTimeOut;
    }

    public void setSocketTimeOut(Integer socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    public Integer getConnectionRequestTimeOut() {
        return connectionRequestTimeOut;
    }

    public void setConnectionRequestTimeOut(Integer connectionRequestTimeOut) {
        this.connectionRequestTimeOut = connectionRequestTimeOut;
    }
}
*/
