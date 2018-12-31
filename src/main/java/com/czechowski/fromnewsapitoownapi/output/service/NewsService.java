package com.czechowski.fromnewsapitoownapi.output.service;

import com.czechowski.fromnewsapitoownapi.output.decorator.NewsDecorator;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public interface NewsService {

    NewsDecorator findByCountryAndCategory(String country, String category, String page, String pageSize, String queryToSearch);
}
