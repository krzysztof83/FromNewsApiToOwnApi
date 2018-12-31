package com.czechowski.fromnewsapitoownapi.output.converter;

import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import com.czechowski.fromnewsapitoownapi.output.model.News;
import com.czechowski.fromnewsapitoownapi.output.model.NewsImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@Component
public class TopHeadLineToNewsConverter {

    ArticleToNewsArticleConverter articleToNewsArticleConverter;

    public TopHeadLineToNewsConverter(ArticleToNewsArticleConverter articleToNewsArticleConverter) {
        this.articleToNewsArticleConverter = articleToNewsArticleConverter;
    }

    public News convert(TopHeadline topHeadline, String country, String category) {
        News news = new NewsImpl();
        news.setCountry(country);
        news.setCategory(category);

        if (topHeadline != null && topHeadline.getArticles() != null) {
            Stream.of(topHeadline.getArticles())
                    .map(articles -> articleToNewsArticleConverter.convert(articles))
                    .collect(Collectors.toCollection(news::getResponseArticles));
        }
        return news;
    }
}
