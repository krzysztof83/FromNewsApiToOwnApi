package com.czechowski.fromnewsapitoownapi.output.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class News {

    private String country;
    private String category;
    List<NewsArticle> responseArticles = new ArrayList<>();

    public String getCountry() {
        return country;
    }

    public News setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public News setCategory(String category) {
        this.category = category;
        return this;
    }

    public List<NewsArticle> getResponseArticles() {
        return responseArticles;
    }

    @Override
    public String toString() {
        return "News{" +
                "country='" + country + '\'' +
                ", category='" + category + '\'' +
                ", responseArticles=" + responseArticles +
                '}';
    }
}
