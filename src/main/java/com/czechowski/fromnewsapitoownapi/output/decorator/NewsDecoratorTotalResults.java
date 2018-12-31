package com.czechowski.fromnewsapitoownapi.output.decorator;

import com.czechowski.fromnewsapitoownapi.output.model.News;
import com.czechowski.fromnewsapitoownapi.output.model.NewsArticle;

import java.util.List;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class NewsDecoratorTotalResults implements NewsDecorator {

    private int totalResults;
    private News news;

    public NewsDecoratorTotalResults(int totalResults, News news) {
        this.totalResults = totalResults;
        this.news = news;
    }

    @Override
    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public String getCountry() {
        return news.getCountry();
    }

    @Override
    public void setCountry(String country) {
        news.setCountry(country);
    }

    @Override
    public String getCategory() {
        return news.getCategory();
    }

    @Override
    public void setCategory(String category) {
        news.setCategory(category);
    }

    @Override
    public List<NewsArticle> getResponseArticles() {
        return news.getResponseArticles();
    }
}
