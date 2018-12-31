package com.czechowski.fromnewsapitoownapi.output.converter;

import com.czechowski.fromnewsapitoownapi.input.model.Article;
import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import com.czechowski.fromnewsapitoownapi.output.model.News;
import com.czechowski.fromnewsapitoownapi.output.model.NewsArticle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class TopHeadLineToNewsImplConverterTest {

    private TopHeadline TOP_HEADLINE;
    private String PL = "pl";
    private String SPORTS = "sports";
    private String EMPTY_STRING = "";

    private TopHeadLineToNewsConverter converter;

    @Before
    public void setUp() {

        converter = new TopHeadLineToNewsConverter(new ArticleToNewsArticleConverter());

        TOP_HEADLINE = new TopHeadline();
        TOP_HEADLINE.setStatus("ok");
        TOP_HEADLINE.setTotalResults("1");

        Article article = new Article();

        Article[] articles = new Article[1];
        articles[0]=article;

        TOP_HEADLINE.setArticles(articles);

    }

    @Test
    public void convert() {

        News actual = converter.convert(TOP_HEADLINE, PL, SPORTS);

        assertNotNull(actual);
    }

    @Test
    public void convertFullObject() {

        News actual = converter.convert(TOP_HEADLINE, PL, SPORTS);

        assertNotNull(actual);

        assertEquals(PL, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());

        assertNotNull(actual.getResponseArticles());
        assertEquals(1, actual.getResponseArticles().size());

        NewsArticle newsArticle = actual.getResponseArticles().get(0);

        assertNotNull(newsArticle);

    }

    @Test
    public void convertNull() {

        News actual = converter.convert(null, PL, SPORTS);

        assertNotNull(actual);

        assertEquals(PL, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());

        assertNotNull(actual.getResponseArticles());
        assertEquals(0, actual.getResponseArticles().size());
    }

    @Test
    public void convertEmpty() {

        News actual = converter.convert(new TopHeadline(), PL, SPORTS);

        assertNotNull(actual);

        assertEquals(PL, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());

        assertNotNull(actual.getResponseArticles());
        assertEquals(0, actual.getResponseArticles().size());
    }

    @Test
    public void convertCountryNull() {

        News actual = converter.convert(TOP_HEADLINE, null, SPORTS);

        assertNotNull(actual);
        assertNull(actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());
    }

    @Test
    public void convertCountryEmpty() {

        News actual = converter.convert(TOP_HEADLINE, EMPTY_STRING, SPORTS);

        assertNotNull(actual);
        assertEquals(EMPTY_STRING, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());
    }

    @Test
    public void convertCategoryNull() {

        News actual = converter.convert(TOP_HEADLINE, PL, null);

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertNull(actual.getCategory());
    }

    @Test
    public void convertCategoryEmpty() {

        News actual = converter.convert(TOP_HEADLINE, PL, EMPTY_STRING);

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(EMPTY_STRING, actual.getCategory());
    }

}