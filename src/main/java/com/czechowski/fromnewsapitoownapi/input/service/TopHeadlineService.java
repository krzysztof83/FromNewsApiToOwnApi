package com.czechowski.fromnewsapitoownapi.input.service;

import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public interface TopHeadlineService {

    TopHeadline findByCountryAndCategory(String country, String category, int page, int pageSize);
}
