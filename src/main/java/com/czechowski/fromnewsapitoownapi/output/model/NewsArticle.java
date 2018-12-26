package com.czechowski.fromnewsapitoownapi.output.model;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class NewsArticle {

    private String author;
    private String title;
    private String description;
    private String date;
    private String sourceName;
    private String articleUrl;
    private String imageUrl;

    public String getAuthor() {
        return author;
    }

    public NewsArticle setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsArticle setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsArticle setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDate() {
        return date;
    }

    public NewsArticle setDate(String date) {
        this.date = date;
        return this;
    }

    public String getSourceName() {
        return sourceName;
    }

    public NewsArticle setSourceName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public NewsArticle setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public NewsArticle setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
