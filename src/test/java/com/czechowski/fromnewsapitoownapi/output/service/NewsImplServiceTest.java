package com.czechowski.fromnewsapitoownapi.output.service;

import com.czechowski.fromnewsapitoownapi.input.model.Article;
import com.czechowski.fromnewsapitoownapi.input.model.Source;
import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import com.czechowski.fromnewsapitoownapi.input.service.TopHeadlineService;
import com.czechowski.fromnewsapitoownapi.output.converter.ArticleToNewsArticleConverter;
import com.czechowski.fromnewsapitoownapi.output.converter.TopHeadLineToNewsConverter;
import com.czechowski.fromnewsapitoownapi.output.exception.PaginationParameterException;
import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCategoryParameterException;
import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCountryParameterException;
import com.czechowski.fromnewsapitoownapi.output.model.News;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class NewsImplServiceTest {

    private final String EMPTY_STRING = "";
    private final String PL = "pl";
    private final String SPORTS = "sports";

    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_PAGE_SIZE = "20";

    private static final String PAGE_NOT_A_NUMBER_ = "as";
    private static final String PAGE_SIZE_NOT_A_NUMBER = "asd";

    private static final String QUERY_TO_SEARCH = "description to test";


    private NewsService newsService;

    @Mock
    TopHeadlineService topHeadlinesService;

    private TopHeadline FULL_TOPHEADLINES_OBJECT;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        newsService = new NewsServiceImpl(topHeadlinesService, new TopHeadLineToNewsConverter(new ArticleToNewsArticleConverter()));

        Article[] articleTable = new Article[1];

        Source source = new Source();
        source.setId("1");
        source.setName("NAME");

        Article article = new Article();
        article.setSource(source);

        article.setAuthor("author");
        article.setTitle("title");
        article.setDescription("description to test");
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

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), nullable(String.class))).thenReturn(FULL_TOPHEADLINES_OBJECT);

        News actual = newsService.findByCountryAndCategory(PL, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, null);

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());

    }

    @Test
    public void findByCountryAndCategoryCountryNull() {

        when(topHeadlinesService.findByCountryAndCategory(nullable(String.class), anyString(), anyInt(), anyInt(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);


        News actual = newsService.findByCountryAndCategory(null, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, "");

        assertNotNull(actual);
        assertNull(actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());
    }

    @Test
    public void findByCountryAndCategoryCountryEmpty() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);


        News actual = newsService.findByCountryAndCategory(EMPTY_STRING, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, "");

        assertNotNull(actual);
        assertEquals(EMPTY_STRING, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());
    }

    @Test
    public void findByCountryAndCategoryCategoryNull() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), nullable(String.class), anyInt(), anyInt(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);


        News actual = newsService.findByCountryAndCategory(PL, null, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, "");

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertNull(actual.getCategory());
    }

    @Test
    public void findByCountryAndCategoryCategoryEmpty() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);

        News actual = newsService.findByCountryAndCategory(PL, EMPTY_STRING, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, "");

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(EMPTY_STRING, actual.getCategory());
    }

    @Test(expected = UnsupportedCountryParameterException.class)
    public void findByCountryAndCategoryCountryNotCorrect() {

        TopHeadline topHeadline = new TopHeadline();

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        News actual = newsService.findByCountryAndCategory("asd", SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, "");

        assertNotNull(actual);
        assertEquals(EMPTY_STRING, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());
    }

    @Test(expected = UnsupportedCategoryParameterException.class)
    public void findByCountryAndCategoryCategoryNotCorrect() {

        TopHeadline topHeadline = new TopHeadline();

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        News actual = newsService.findByCountryAndCategory(PL, "asd", DEFAULT_PAGE, DEFAULT_PAGE_SIZE, "");

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(EMPTY_STRING, actual.getCategory());
    }

    @Test(expected = PaginationParameterException.class)
    public void findByCountryAndCategoryPageParameterNotANumber() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);

        News actual = newsService.findByCountryAndCategory(PL, SPORTS, PAGE_NOT_A_NUMBER_, DEFAULT_PAGE_SIZE, "");

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());

    }

    @Test(expected = PaginationParameterException.class)
    public void findByCountryAndCategoryPageSizeParameterNotANumber() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);

        News actual = newsService.findByCountryAndCategory(PL, SPORTS, DEFAULT_PAGE, PAGE_SIZE_NOT_A_NUMBER, "");

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());

    }

    @Test(expected = PaginationParameterException.class)
    public void findByCountryAndCategoryPageAndPageSIzeParameterNotANumber() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);

        News actual = newsService.findByCountryAndCategory(PL, SPORTS, PAGE_NOT_A_NUMBER_, PAGE_SIZE_NOT_A_NUMBER, "");

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());

    }

    @Test
    public void findByCountryAndCategoryWithQuery() {

        when(topHeadlinesService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(FULL_TOPHEADLINES_OBJECT);

        News actual = newsService.findByCountryAndCategory(PL, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, QUERY_TO_SEARCH);

        assertNotNull(actual);
        assertEquals(PL, actual.getCountry());
        assertEquals(SPORTS, actual.getCategory());
        assertTrue(actual.getResponseArticles().get(0).getDescription().contains(QUERY_TO_SEARCH));

    }

}