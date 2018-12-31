package com.czechowski.fromnewsapitoownapi.output.model;

import java.util.List;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public interface News {

    String getCountry();

    void setCountry(String country);

    String getCategory();

    void setCategory(String category);

    List<NewsArticle> getResponseArticles();

    String toString();
}
