package com.groundcollector.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;

@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiConfig {

    @Value("${api.api-key-header}")
    private String apiKeyHeader;

    @Value("${api.api-key}")
    private String apiKey;

    @Value("${api.api-host-header}")
    private String apiHostHeader;

    @Value("${api.api-host}")
    private String apiHost;

    public String getApiKeyHeader() {
        return apiKeyHeader;
    }

    public void setApiKeyHeader(String apiKeyHeader) {
        this.apiKeyHeader = apiKeyHeader;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiHostHeader() {
        return apiHostHeader;
    }

    public void setApiHostHeader(String apiHostHeader) {
        this.apiHostHeader = apiHostHeader;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    /*public ApiConfig(String apiKeyHeader, String apiKey, String apiHostHeader, String apiHost) {
        this.apiKeyHeader = apiKeyHeader;
        this.apiKey = apiKey;
        this.apiHostHeader = apiHostHeader;
        this.apiHost = apiHost;
    }

     */
}
