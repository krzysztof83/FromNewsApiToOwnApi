package com.czechowski.fromnewsapitoownapi.input.model;

import java.util.Arrays;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class TopHeadline {

    private String status;
    private String totalResults;
    private Article articles[];

    public String getStatus() {
        return status;
    }

    public TopHeadline setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public TopHeadline setTotalResults(String totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Article[] getArticles() {
        return articles;
    }

    public TopHeadline setArticles(Article[] articles) {
        this.articles = articles;
        return this;
    }

    @Override
    public String toString() {
        return "TopHeadline{" +
                "status='" + status + '\'' +
                ", totalResults='" + totalResults + '\'' +
                ", articles=" + Arrays.toString(articles) +
                '}';
    }
}
