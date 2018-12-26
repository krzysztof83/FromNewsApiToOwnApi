package com.czechowski.fromnewsapitoownapi.output.service;

import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import com.czechowski.fromnewsapitoownapi.input.service.TopHeadlineService;
import com.czechowski.fromnewsapitoownapi.output.converter.ArticleToNewsArticleConverter;
import com.czechowski.fromnewsapitoownapi.output.model.News;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@Service
public class NewsServiceImpl implements NewsService {

    private TopHeadlineService topHeadlineService;
    private ArticleToNewsArticleConverter articleToNewsArticleConverter;

    public NewsServiceImpl(TopHeadlineService topHeadlineService, ArticleToNewsArticleConverter articleToNewsArticleConverter) {
        this.topHeadlineService = topHeadlineService;
        this.articleToNewsArticleConverter = articleToNewsArticleConverter;
    }


    public News findByCountryAndCategory(String country, String category)  {

        TopHeadline topHeadline = topHeadlineService.findByCountryAndCategory(country,category);

        News news = new News();
        news.setCountry(country);
        news.setCategory(category);

        if (topHeadline.getArticles() != null) {
            Stream.of(topHeadline.getArticles())
                    .map(articles -> articleToNewsArticleConverter.convert(articles))
                    .collect(Collectors.toCollection(news::getResponseArticles));
        }

        return news;
    }
}
