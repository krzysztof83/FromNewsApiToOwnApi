package com.czechowski.fromnewsapitoownapi.input.service;

import com.czechowski.fromnewsapitoownapi.input.config.NewsApiOrgConfig;
import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@Service
public class TopHeadlinesServiceImpl implements TopHeadlineService {

    private RestTemplate restTemplate;

    NewsApiOrgConfig newsApiOrgConfig;

    public TopHeadlinesServiceImpl(RestTemplateBuilder restTemplateBuilder, NewsApiOrgConfig newsApiOrgConfig) {
        this.restTemplate = restTemplateBuilder.build();
        this.newsApiOrgConfig = newsApiOrgConfig;

    }

    public TopHeadline findByCountryAndCategory(String country, String category, int page, int pageSize, String queryToSearch) {

        String uri = getUriWithPagination(country, category, page, pageSize, queryToSearch);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<TopHeadline> responseObj2 = restTemplate.exchange(
                uri, HttpMethod.GET, entity, TopHeadline.class);

        return responseObj2.getBody();
    }

    private String getUriWithPagination(String country, String category, int page, int pageSize, String queryToSearch) {

        return UriComponentsBuilder.fromHttpUrl(newsApiOrgConfig.getNewsApiUrl().trim())
                .queryParam("country", country)
                .queryParam("category", category)
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("q",queryToSearch)
                .queryParam("apiKey", newsApiOrgConfig.getApiKey().trim())
                .encode().toUriString();
    }

}
