package com.czechowski.fromnewsapitoownapi.output.service;

import com.czechowski.fromnewsapitoownapi.output.model.News;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public interface NewsService {

    News findByCountryAndCategory(String country, String category, int page, int pageSize);
}
