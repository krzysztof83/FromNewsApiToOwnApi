package com.czechowski.fromnewsapitoownapi.output.converter;

import com.czechowski.fromnewsapitoownapi.input.model.Article;
import com.czechowski.fromnewsapitoownapi.output.model.NewsArticle;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class ArticleToNewsArticleConverter implements Converter<Article, NewsArticle> {


    @Override
    public NewsArticle convert(Article article) {

        if (article == null) {
            return null;
        }

        NewsArticle newsArticle = new NewsArticle();

        newsArticle.setAuthor(article.getAuthor());
        newsArticle.setTitle(article.getTitle());
        newsArticle.setDescription(article.getDescription());

        newsArticle.setDate(convertDate(article.getPublishedAt()));
        System.out.println(newsArticle.getDate());

        String sourceName = null;
        if (article.getSource() != null) {
            sourceName = article.getSource().getName();
        }

        newsArticle.setSourceName(sourceName);
        newsArticle.setArticleUrl(article.getUrl());
        newsArticle.setImageUrl(article.getUrlToImage());

        return newsArticle;
    }

    private String convertDate(String dateInString) {

        if (dateInString == null) {
            return null;
        }

        if (dateInString.length() == 0) {
            return "";
        }

        String dateInNewFormat;

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date d = input.parse(dateInString);
            dateInNewFormat = output.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
            /* if the data format is incorrect,
               return the following information:
               Unparseable date: "2018-12-2asfd5T12:58:29Z"
               to give the customer a chance to recover data, in this case year or month
             */
            dateInNewFormat = e.getMessage();
        }

        return dateInNewFormat;
    }
}
