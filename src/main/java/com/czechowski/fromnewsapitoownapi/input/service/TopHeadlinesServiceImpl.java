package com.czechowski.fromnewsapitoownapi.input.service;

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

    static final String API_KEY = "c35f39498cda43069cdd4f3c24c4740a";

    RestTemplate restTemplate;

    public TopHeadlinesServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public TopHeadline findByCountryAndCategory(String country, String category) {

        String uri = getUri(country, category);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<TopHeadline> responseObj2 = restTemplate.exchange(
                uri, HttpMethod.GET, entity, TopHeadline.class);

        return responseObj2.getBody();
    }

    private String getUri(String country, String category) {
        return UriComponentsBuilder.fromHttpUrl("https://newsapi.org/v2/top-headlines")
                .queryParam("country", country)
                .queryParam("category", category)
                .queryParam("apiKey", API_KEY).toUriString();
    }
}
