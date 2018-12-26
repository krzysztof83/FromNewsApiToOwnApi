package com.czechowski.fromnewsapitoownapi.output.service;

import com.czechowski.fromnewsapitoownapi.input.model.Article;
import com.czechowski.fromnewsapitoownapi.input.model.Source;
import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import com.czechowski.fromnewsapitoownapi.input.service.TopHeadlineService;
import com.czechowski.fromnewsapitoownapi.output.converter.ArticleToNewsArticleConverter;
import com.czechowski.fromnewsapitoownapi.output.model.News;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class NewsServiceTest {

    private final String EMPTY_STRING ="";
    private final String PL ="pl";
    private final String SPORTS ="sports";

    private NewsService newsService;

    private ArticleToNewsArticleConverter articleConverter = new ArticleToNewsArticleConverter();

    @Mock
    TopHeadlineService topHeadlinesService;

    private TopHeadline FULL_TOPHEADLINES_OBJECT;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        newsService = new NewsServiceImpl(topHeadlinesService, articleConverter);

        Article[] articleTable = new Article[1];

        Source source = new Source();
        source.setId("1");
        source.setName("NAME");

        Article article = new Article();
        article.setSource(source);

        article.setAuthor("author");
        article.setTitle("title");
        article.setDescription("description");
        article.setUrl("url");
        article.setUrlToImage("uriToImage");
        article.setPublishedAt("2018-12-25T12:58:29Z");
        article.setContent("content");

        articleTable[0] = article;

        FULL_TOPHEADLINES_OBJECT = new TopHeadline();
        FULL_TOPHEADLINES_OBJECT.setStatus("status");
        FULL_TOPHEADLINES_OBJECT.setTotalResults("2");
        FULL_TOPHEADLINES_OBJECT.setArticles(articleTable);


    }

    @Test
    public void findByCountryAndCategory() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);

        News actual = newsService.findByCountryAndCategory(PL, SPORTS);

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());

    }

    @Test
    public void findByCountryAndCategoryCountryNull() {

        when(topHeadlinesService.findByCountryAndCategory(nullable(String.class), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);


        News actual = newsService.findByCountryAndCategory(null, SPORTS);

        assertNotNull(actual);
        assertEquals(null, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());
    }

    @Test
    public void findByCountryAndCategoryCountryEmpty() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);


        News actual = newsService.findByCountryAndCategory(EMPTY_STRING, SPORTS);

        assertNotNull(actual);
        assertEquals(EMPTY_STRING, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());
    }

    @Test
    public void findByCountryAndCategoryCategoryNull() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), nullable(String.class))).thenReturn(FULL_TOPHEADLINES_OBJECT);


        News actual = newsService.findByCountryAndCategory(PL, null);

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(null, actual.getCategory());
    }

    @Test
    public void findByCountryAndCategoryCategoryEmpty() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);

        News actual = newsService.findByCountryAndCategory(PL, EMPTY_STRING);

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(EMPTY_STRING, actual.getCategory());
    }

    @Ignore
    @Test
    public void findByCountryAndCategoryCountryNotCorrect() {

        TopHeadline topHeadline = new TopHeadline();

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString())).thenReturn(topHeadline);

        News actual = newsService.findByCountryAndCategory("asd", SPORTS);

        assertNotNull(actual);
        assertEquals(EMPTY_STRING, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());
    }

    @Ignore
    @Test
    public void findByCountryAndCategoryCategoryNotCorrect() {

        TopHeadline topHeadline = new TopHeadline();

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString())).thenReturn(topHeadline);

        News actual = newsService.findByCountryAndCategory(PL, "asd");

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(EMPTY_STRING, actual.getCategory());
    }
}