package com.czechowski.fromnewsapitoownapi.output.controller;

import com.czechowski.fromnewsapitoownapi.input.model.Article;
import com.czechowski.fromnewsapitoownapi.input.model.Source;
import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import com.czechowski.fromnewsapitoownapi.input.service.TopHeadlineService;
import com.czechowski.fromnewsapitoownapi.output.converter.ArticleToNewsArticleConverter;
import com.czechowski.fromnewsapitoownapi.output.converter.TopHeadLineToNewsConverter;
import com.czechowski.fromnewsapitoownapi.output.decorator.NewsDecorator;
import com.czechowski.fromnewsapitoownapi.output.decorator.NewsDecoratorTotalResults;
import com.czechowski.fromnewsapitoownapi.output.model.News;
import com.czechowski.fromnewsapitoownapi.output.service.NewsService;
import com.czechowski.fromnewsapitoownapi.output.service.NewsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class NewsImplControllerTest {

    private static final String PL = "pl";
    private static final String SPORTS = "sports";
    private static final String DESCRIPTION = "description";


    private static final String NEWS_PL_SPORTS = "/news/" + PL + "/" + SPORTS;
    private static final String NEWS_PL_SPORTS_WITH_PAGINATION = "/news/" + PL + "/" + SPORTS + "?page=2&pageSize=5";
    private static final String BAD_REQUEST_COUNTRY = "/news/pasdl/" + SPORTS;
    private static final String BAD_REQUEST_CATEGORY = "/news/" + PL + "/asd";
    private static final String BAD_REQUEST_COUNTRY_AND_CATEGORY = "/news/pasdl/asdsf";

    private static final String BAD_REQUEST_PAGE_NOT_A_NUMBER = NEWS_PL_SPORTS + "?page=asd&pageSize=5";
    private static final String BAD_REQUEST_PAGE_SIZE_NOT_A_NUMBER = NEWS_PL_SPORTS + "?page=2&pageSize=asd";

    private static final String NEWS_PL_SPORTS_QUERY = "/news/" + PL + "/" + SPORTS + "?q=" + DESCRIPTION;

    private TopHeadline topHeadline;

    private TopHeadLineToNewsConverter topHeadLineToNewsConverter;
    private NewsService newsService;

    @Mock
    TopHeadlineService topHeadlineService;

    private NewsController newsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        topHeadLineToNewsConverter = new TopHeadLineToNewsConverter(new ArticleToNewsArticleConverter());
        newsService = new NewsServiceImpl(topHeadlineService, topHeadLineToNewsConverter);

        newsController = new NewsController(newsService);
        mockMvc = MockMvcBuilders.standaloneSetup(newsController).setControllerAdvice(ControllerExceptionHandler.class)
                .build();

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

        topHeadline = new TopHeadline();
        topHeadline.setStatus("status");
        topHeadline.setTotalResults("2");
        topHeadline.setArticles(articleTable);
    }

    @Test
    public void getNewsByCountryAndCategoryStatusTest() throws Exception {

        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        mockMvc.perform(get(NEWS_PL_SPORTS))
                .andExpect(status().isOk());

        mockMvc.perform(get(NEWS_PL_SPORTS)).andReturn().getResponse();
    }

    @Test
    public void getNewsByCountryAndCategoryWithPaginatin() throws Exception {

        System.out.println(NEWS_PL_SPORTS_WITH_PAGINATION);

        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        mockMvc.perform(get(NEWS_PL_SPORTS_WITH_PAGINATION))
                .andExpect(status().isOk());

        mockMvc.perform(get(NEWS_PL_SPORTS_WITH_PAGINATION)).andReturn().getResponse();
    }

    @Test
    public void getNewsByCountryAndCategoryContentTest() throws Exception {

        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        News news = topHeadLineToNewsConverter.convert(topHeadline, PL, SPORTS);
        int totalResults = Integer.valueOf(topHeadline.getTotalResults());
        NewsDecorator newsDecorator = new NewsDecoratorTotalResults(totalResults, news);

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(newsDecorator);
        System.out.println(jsonString);

        MockHttpServletResponse response = mockMvc.perform(get(NEWS_PL_SPORTS)).andReturn().getResponse();
        System.out.println(response.getContentAsString());

        mockMvc.perform(get(NEWS_PL_SPORTS))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonString));

    }

    @Test
    public void getNewsByCountryAndCategoryBadRequestCountry() throws Exception {

        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        mockMvc.perform(get(BAD_REQUEST_COUNTRY))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(BAD_REQUEST_COUNTRY)).andReturn().getResponse();
    }

    @Test
    public void getNewsByCountryAndCategoryBadRequestCategory() throws Exception {

        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        mockMvc.perform(get(BAD_REQUEST_CATEGORY))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(BAD_REQUEST_CATEGORY)).andReturn().getResponse();
    }

    @Test
    public void getNewsByCountryAndCategoryBadRequestCountryAndCategory() throws Exception {

        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        mockMvc.perform(get(BAD_REQUEST_COUNTRY_AND_CATEGORY))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(BAD_REQUEST_COUNTRY_AND_CATEGORY)).andReturn().getResponse();
    }

    @Test
    public void getNewsByCountryAndCategoryBadRequestPageParameterNotANumber() throws Exception {

        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        mockMvc.perform(get(BAD_REQUEST_PAGE_NOT_A_NUMBER))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(BAD_REQUEST_PAGE_NOT_A_NUMBER)).andReturn().getResponse();
    }

    @Test
    public void getNewsByCountryAndCategoryBadRequestPageSizeParameterNotANumber() throws Exception {

        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        mockMvc.perform(get(BAD_REQUEST_PAGE_SIZE_NOT_A_NUMBER))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(BAD_REQUEST_PAGE_SIZE_NOT_A_NUMBER)).andReturn().getResponse();
    }


    @Test
    public void getNewsByCountryAndCategory_WithQueryContentTest() throws Exception {

        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        News news = topHeadLineToNewsConverter.convert(topHeadline, PL, SPORTS);
        int totalResults = Integer.valueOf(topHeadline.getTotalResults());
        NewsDecorator newsDecorator = new NewsDecoratorTotalResults(totalResults, news);

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(newsDecorator);

        mockMvc.perform(get(NEWS_PL_SPORTS_QUERY))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonString));

        MockHttpServletResponse response = mockMvc.perform(get(NEWS_PL_SPORTS_QUERY)).andReturn().getResponse();
        assertTrue(response.getContentAsString().contains(DESCRIPTION));
    }

    @Test
    public void getNewsByCountryAndCategoryHeaderLinksFirstAndNextAndPrevAndLastTest() throws Exception {

        topHeadline.setTotalResults("21");
        when(topHeadlineService.findByCountryAndCategory(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(topHeadline);

        mockMvc.perform(get(NEWS_PL_SPORTS_WITH_PAGINATION))
                .andExpect(status().isOk());

        MockHttpServletResponse response = mockMvc.perform(get(NEWS_PL_SPORTS_WITH_PAGINATION)).andReturn().getResponse();

        String first = response.getHeader("first");
        String next = response.getHeader("next");
        String prev = response.getHeader("prev");
        String last = response.getHeader("last");


        String firstLink = "http://localhost/news/pl/sports?page=0&pageSize=5";
        String nextLink = "http://localhost/news/pl/sports?page=3&pageSize=5";
        String prevLink = "http://localhost/news/pl/sports?page=1&pageSize=5";
        String lastLink = "http://localhost/news/pl/sports?page=5&pageSize=5";

        assertEquals(firstLink, first);
        assertEquals(nextLink, next);
        assertEquals(prevLink, prev);
        assertEquals(lastLink, last);
    }



}