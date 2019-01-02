package com.czechowski.fromnewsapitoownapi.input.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@Configuration
@PropertySource("classpath:application.properties")
public class NewsApiOrgConfig {

    @Value(" ${newsapiorg.apikey} ")
    private String apiKey;

    @Value("${newsapiorg.url}")
    private String newsApiUrl;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getNewsApiUrl() {
        return newsApiUrl;
    }

    public void setNewsApiUrl(String newsApiUrl) {
        this.newsApiUrl = newsApiUrl;
    }
}
