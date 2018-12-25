package com.czechowski.fromnewsapitoownapi.input.service;

import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@Service
public class TopHeadlinesServiceImpl implements TopHeadlineService{

    RestTemplate restTemplate;

    public TopHeadlinesServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public TopHeadline findByCountryAndCategory(String country, String category) {
        return null;
    }
}
