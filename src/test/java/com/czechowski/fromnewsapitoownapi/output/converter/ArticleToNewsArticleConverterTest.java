package com.czechowski.fromnewsapitoownapi.output.converter;

import com.czechowski.fromnewsapitoownapi.input.model.Article;
import com.czechowski.fromnewsapitoownapi.input.model.Source;
import com.czechowski.fromnewsapitoownapi.output.model.NewsArticle;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class ArticleToNewsArticleConverterTest {

    Article FULL_ARTICLE_OBJECT;

    ArticleToNewsArticleConverter converter = new ArticleToNewsArticleConverter();

    @Before
    public void setUp() throws Exception {

        Source source = new Source();
        source.setId("1");
        source.setName("NAME");

        FULL_ARTICLE_OBJECT = new Article();
        FULL_ARTICLE_OBJECT.setSource(source);

        FULL_ARTICLE_OBJECT.setAuthor("author");
        FULL_ARTICLE_OBJECT.setTitle("title");
        FULL_ARTICLE_OBJECT.setDescription("description");
        FULL_ARTICLE_OBJECT.setUrl("url");
        FULL_ARTICLE_OBJECT.setUrlToImage("uriToImage");
        FULL_ARTICLE_OBJECT.setPublishedAt("2018-12-25T12:58:29Z");
        FULL_ARTICLE_OBJECT.setContent("content");

    }

    @Test
    public void convertFullObject() {

        NewsArticle actual = converter.convert(FULL_ARTICLE_OBJECT);

        assertNotNull(actual);
        assertEquals(FULL_ARTICLE_OBJECT.getSource().getName(), actual.getSourceName());
        assertEquals(FULL_ARTICLE_OBJECT.getDescription(), actual.getDescription());
    }

    @Test
    public void convertSourceNull() {

        FULL_ARTICLE_OBJECT.setSource(null);

        NewsArticle actual = converter.convert(FULL_ARTICLE_OBJECT);

        assertNotNull(actual);
        assertNull(actual.getSourceName());
        assertEquals(FULL_ARTICLE_OBJECT.getDescription(), actual.getDescription());
    }

    @Test
    public void convertSourceNameNull() {

        FULL_ARTICLE_OBJECT.getSource().setName(null);

        NewsArticle actual = converter.convert(FULL_ARTICLE_OBJECT);

        assertNotNull(actual);
        assertNull(actual.getSourceName());
        assertEquals(FULL_ARTICLE_OBJECT.getDescription(), actual.getDescription());
    }

    @Test
    public void convertSourceNameEmpty() {

        FULL_ARTICLE_OBJECT.getSource().setName("");

        NewsArticle actual = converter.convert(FULL_ARTICLE_OBJECT);

        assertNotNull(actual);
        assertEquals(FULL_ARTICLE_OBJECT.getSource().getName(), actual.getSourceName());
        assertEquals(FULL_ARTICLE_OBJECT.getDescription(), actual.getDescription());
    }

    @Test
    public void convertDescriptionEmpty() {

        FULL_ARTICLE_OBJECT.setDescription("");

        NewsArticle actual = converter.convert(FULL_ARTICLE_OBJECT);

        assertNotNull(actual);
        assertEquals(FULL_ARTICLE_OBJECT.getSource().getName(), actual.getSourceName());
        assertEquals(FULL_ARTICLE_OBJECT.getDescription(), actual.getDescription());
    }

    @Test
    public void convertDescriptionNull() {

        FULL_ARTICLE_OBJECT.setDescription(null);

        NewsArticle actual = converter.convert(FULL_ARTICLE_OBJECT);

        assertNotNull(actual);
        assertEquals(FULL_ARTICLE_OBJECT.getSource().getName(), actual.getSourceName());
        assertEquals(FULL_ARTICLE_OBJECT.getDescription(), actual.getDescription());
    }

    @Test
    public void convertPublishedAtNull() {

        FULL_ARTICLE_OBJECT.setPublishedAt(null);

        NewsArticle actual = converter.convert(FULL_ARTICLE_OBJECT);

        assertNotNull(actual);
        assertEquals(FULL_ARTICLE_OBJECT.getSource().getName(), actual.getSourceName());
        assertNull(actual.getDate());
    }

    @Test
    public void convertPublishedAtEmpty() {

        FULL_ARTICLE_OBJECT.setPublishedAt("");

        NewsArticle actual = converter.convert(FULL_ARTICLE_OBJECT);

        assertNotNull(actual);
        assertEquals(FULL_ARTICLE_OBJECT.getSource().getName(), actual.getSourceName());
        assertEquals("", actual.getDate());
    }

    @Test
    public void convertPublishedAtWrongFormat() {

        String PUBLISHED_AT_WRONG_FORMAT = "2018-12-2asfd5T12:58:29Z";
        FULL_ARTICLE_OBJECT.setPublishedAt(PUBLISHED_AT_WRONG_FORMAT);

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String unparseableDateMessage = null;
        try {
            input.parse(PUBLISHED_AT_WRONG_FORMAT);
        } catch (ParseException e) {
            e.printStackTrace();
            unparseableDateMessage = e.getMessage();
        }

        NewsArticle actual = converter.convert(FULL_ARTICLE_OBJECT);

        assertNotNull(actual);
        assertEquals(FULL_ARTICLE_OBJECT.getSource().getName(), actual.getSourceName());
        assertEquals(unparseableDateMessage, actual.getDate());
    }


}