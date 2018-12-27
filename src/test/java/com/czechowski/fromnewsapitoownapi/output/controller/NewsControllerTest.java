package com.czechowski.fromnewsapitoownapi.output.controller;

import com.czechowski.fromnewsapitoownapi.output.model.News;
import com.czechowski.fromnewsapitoownapi.output.model.NewsArticle;
import com.czechowski.fromnewsapitoownapi.output.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class NewsControllerTest {

    private static final String NEWS_PL_SPORTS = "/news/pl/sports";
    private News news;

    @Mock
    NewsService newsService;

    private NewsController newsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        newsController = new NewsController(newsService);
        mockMvc = MockMvcBuilders.standaloneSetup(newsController)
                .build();

        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setAuthor("author")
                .setTitle("title")
                .setDescription("description")
                .setDate("2018-05-21")
                .setSourceName("sourceName")
                .setArticleUrl("articleUrl")
                .setImageUrl("imageUrl");

        news = new News();
        news.setCountry("pl").setCategory("sports");
        news.getResponseArticles().add(newsArticle);
    }

    @Test
    public void getNewsByCountryAndCategoryStatusTest() throws Exception {

        when(newsService.findByCountryAndCategory(anyString(),anyString())).thenReturn(news);

        mockMvc.perform(get(NEWS_PL_SPORTS))
                .andExpect(status().isOk());

        mockMvc.perform(get(NEWS_PL_SPORTS)).andReturn().getResponse();
    }

    @Test
    public void getNewsByCountryAndCategoryContentTest() throws Exception {

        when(newsService.findByCountryAndCategory(anyString(),anyString())).thenReturn(news);

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(news);

        mockMvc.perform(get(NEWS_PL_SPORTS))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonString));

        mockMvc.perform(get(NEWS_PL_SPORTS)).andReturn().getResponse();
    }

}