package com.czechowski.fromnewsapitoownapi.output.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class NewsImpl implements News {

    private String country;
    private String category;
    private int totalResult;
    List<NewsArticle> responseArticles = new ArrayList<>();

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    @Override
    public List<NewsArticle> getResponseArticles() {
        return responseArticles;
    }

}
