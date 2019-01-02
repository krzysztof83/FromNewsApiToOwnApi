package com.czechowski.fromnewsapitoownapi.input.service;

import com.czechowski.fromnewsapitoownapi.input.config.NewsApiOrgConfig;
import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@RunWith(SpringRunner.class)
@RestClientTest(TopHeadlinesServiceImpl.class)
@Import( NewsApiOrgConfig.class )
@TestPropertySource("classpath:application.properties")
public class TopHeadlineServiceTest {

    private static final String API_KEY = "apiKey=";

    private static final String AND = "&";

    private static final String COUNTRY = "country";
    private static final String COUNTRY_PL = "country=pl";
    private static final String COUNTRY_NOT_CORRECT = "country=asd";

    private static final String CATEGORY = "category";
    private static final String CATEGORY_SPORTS = "category=sports";
    private static final String CATEGORY_NOT_CORRECT = "category=asd";

    private static final String PAGE_DEFAULT = "page=0";
    private static final String PAGE_NOT_DEFAULT = "page=5";

    private static final String PAGE_SIZE_DEFAULT = "pageSize=20";
    private static final String PAGE_SIZE_NOT_DEFAULT = "pageSize=7";

    private static final String QUERY = "q";
    private static final String QUER_EMPTY = "q=";
    private static final String QUERY_TEST = "q=test";

    private final String PL = "pl";
    private final String SPORTS = "sports";

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    private static final int NOT_DEFAULT_PAGE = 5;
    private static final int NOT_DEFAULT_PAGESIZE = 7;

    private static final String TEST = "test";

    @Autowired
    private TopHeadlineService topHeadlineService;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    NewsApiOrgConfig newsApiOrgConfig;

    @Test
    public void findByCountryAndCategoryTest() {
        String value = "{\"status\":\"ok\",\"totalResults\":\"1\",\"articles\":[{\"source\":{\"id\":null,\"name\":\"Probasket.pl\"},\"author\":null,\"title\":\"NBA: Gortat narzeka na Jokicia - probasket.pl\",\"description\":\"Marcin Gortat docenia talent lidera Denver Nuggets, ale wkurza go jedna rzecz, mianowicie skłonność Nikoli Jokicia do flopowania. Co na to zawodnik Bryłek? Nie\",\"url\":\"http://probasket.pl/nba-gortat-narzeka-na-jokicia/\",\"urlToImage\":\"http://probasket.pl/wp-content/uploads/2018/12/maxresdefault-51.jpg\",\"publishedAt\":\"2018-12-24T10:27:00Z\",\"content\":\"Marcin Gortat docenia talent lidera Denver Nuggets, ale wkurza go jedna rzecz, mianowicie skonno Nikoli Jokicia do flopowania. Co na to zawodnik Bryek? Nie mia adnego konkretnego komentarza do sów rodkowego Los Angeles Clippers. Nikola Joki po dwóch faulach t… [+1085 chars]\"}]}";

        final String NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_APIKEY = createURI(newsApiOrgConfig.getNewsApiUrl(),"?",COUNTRY_PL,AND,CATEGORY_SPORTS,AND,PAGE_DEFAULT,AND,PAGE_SIZE_DEFAULT,AND,QUERY,AND,API_KEY,newsApiOrgConfig.getApiKey().trim());

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_APIKEY))
                .andRespond(withSuccess(value, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory(PL, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE,null);

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("1", topHeadline.getTotalResults());
        assertEquals(1, topHeadline.getArticles().length);
    }

    @Test
    public void findByCountryAndCategory_CountryNull() {

        String value2 = "{\"status\":\"ok\",\"totalResults\":3403,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Mobil.nsd.se\"},\"author\":\"Carl Göransson/TT\",\"title\":\"Kramer blickar framåt efter succésilvret - NSD\",\"description\":\"Friidrott Kungen av genombrott nöjer sig inte med EM-silvret från i somras.Nu laddar Andreas Kramer om för en säsong med trippla mästerskap.Jag känner att det kan bli ett väldigt bra år för mig, säger 800-meterslöparen.\",\"url\":\"https://mobil.nsd.se/sport/kramer-blickar-framat-efter-succsilvret-nm4993444.aspx\",\"urlToImage\":\"https://img8.ntm.eu/nm/public/img/4993447/1225141008/kramer-tranar-infor-ett-hektis?w=980&h=551&mode=crop&scale=both&anchor=topcenter\",\"publishedAt\":\"2018-12-25T13:16:55Z\",\"content\":\"Carl Göransson/TT 14:07 | 2018-12-25 21-årige Andreas Kramers utveckling de senaste åren illustreras kanske bäst av nomineringarna till Svenska friidrottsförbundets årliga gala. 2016 var han nominerad till årets genombrott i landslaget. Samma visa 2017. Och i… [+3118 chars]\"}]}";

        final String NEWSAPI_V2_TOPHEADLINES_NULL_SPORTS_APIKEY = createURI(newsApiOrgConfig.getNewsApiUrl(),"?",COUNTRY,AND,CATEGORY_SPORTS,AND,PAGE_DEFAULT,AND,PAGE_SIZE_DEFAULT,AND, QUERY,AND,API_KEY,newsApiOrgConfig.getApiKey().trim());

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_NULL_SPORTS_APIKEY))
                .andRespond(withSuccess(value2, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory(null, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE,null);

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("3403", topHeadline.getTotalResults());
        assertEquals(1, topHeadline.getArticles().length);
    }

    @Test
    public void findByCountryAndCategory_CountryNotCorrect() {

        String value3 = "{\"status\":\"ok\",\"totalResults\":0,\"articles\":[]}";

        final String NEWSAPI_V2_TOPHEADLINES_COUNTRY_NOT_CORRECT_SPORTS_APIKEY = createURI(newsApiOrgConfig.getNewsApiUrl(),"?",COUNTRY_NOT_CORRECT,AND,CATEGORY_SPORTS,AND,PAGE_DEFAULT,AND,PAGE_SIZE_DEFAULT,AND, QUERY,AND,API_KEY,newsApiOrgConfig.getApiKey().trim());

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_COUNTRY_NOT_CORRECT_SPORTS_APIKEY))
                .andRespond(withSuccess(value3, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory("asd", SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE,null);

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("0", topHeadline.getTotalResults());
        assertEquals(0, topHeadline.getArticles().length);
    }

    @Test
    public void findByCountryAndCategoryCategoryNull() {

        String value4 = "{\"status\":\"ok\",\"totalResults\":28,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Gazeta.pl\"},\"author\":\"mk\",\"title\":\"Grzyb z Puszczy Białowieskiej nadzieją w walce z rakiem. Naukowcy złożyli wniosek patentowy - Wiadomosci Gazeta.pl\",\"description\":\"Niepozorny grzyb, korzeniowiec sosnowy wyst�puj�cy m.in. w Puszczy Bia�owieskiej, mo�e pom�c w walce z nowotworem jelita grubego. Naukowcy z Uniwersytetu Medycznego w Bia�ymstoku i Politechniki Bia�ostockiej z�o�yli wniosek patentowy.\",\"url\":\"http://wiadomosci.gazeta.pl/wiadomosci/7,114883,24309074,grzyb-z-puszczy-bialowieskiej-nadzieja-w-walce-z-rakiem-naukowcy.html\",\"urlToImage\":\"https://bi.im-g.pl/im/60/2e/17/z24309088IER,Badania-nad-korzeniowcem-sosnowym.jpg\",\"publishedAt\":\"2018-12-25T12:28:00Z\",\"content\":\",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, W dniach 21-24 II 2019 papie ma si w Watykanie spotka z kardynaami, biskupami w sprawie pedofilii ksiy i zakonnikw. Dlatego potrzebna … [+427 chars]\"}]}";

        final String NEWSAPI_V2_TOPHEADLINES_PL_NULL_APIKEY = createURI(newsApiOrgConfig.getNewsApiUrl(),"?",COUNTRY_PL,AND,CATEGORY,AND,PAGE_DEFAULT,AND,PAGE_SIZE_DEFAULT,AND, QUERY,AND,API_KEY,newsApiOrgConfig.getApiKey().trim());

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_PL_NULL_APIKEY))
                .andRespond(withSuccess(value4, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory(PL, null, DEFAULT_PAGE, DEFAULT_PAGE_SIZE,null);

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("28", topHeadline.getTotalResults());
        assertEquals(1, topHeadline.getArticles().length);

    }

    @Test
    public void findByCountryAndCategory_CategoryNotCorrect() {

        String value5 = "{\"status\":\"ok\",\"totalResults\":0,\"articles\":[]}";

        final String NEWSAPI_V2_TOPHEADLINES_PL_CATEGORY_NOT_CORRECT_APIKEY = createURI(newsApiOrgConfig.getNewsApiUrl(),"?",COUNTRY_PL,AND,CATEGORY_NOT_CORRECT,AND,PAGE_DEFAULT,AND,PAGE_SIZE_DEFAULT,AND, QUERY,AND,API_KEY,newsApiOrgConfig.getApiKey().trim());

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_PL_CATEGORY_NOT_CORRECT_APIKEY))
                .andRespond(withSuccess(value5, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory(PL, "asd", DEFAULT_PAGE, DEFAULT_PAGE_SIZE,null);

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("0", topHeadline.getTotalResults());
        assertEquals(0, topHeadline.getArticles().length);
    }

    @Test
    public void findByCountryAndCategory_NotDefaultPage() {

        String value5 = "{\"status\":\"ok\",\"totalResults\":28,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Gazeta.pl\"},\"author\":\"mk\",\"title\":\"Grzyb z Puszczy Białowieskiej nadzieją w walce z rakiem. Naukowcy złożyli wniosek patentowy - Wiadomosci Gazeta.pl\",\"description\":\"Niepozorny grzyb, korzeniowiec sosnowy wyst�puj�cy m.in. w Puszczy Bia�owieskiej, mo�e pom�c w walce z nowotworem jelita grubego. Naukowcy z Uniwersytetu Medycznego w Bia�ymstoku i Politechniki Bia�ostockiej z�o�yli wniosek patentowy.\",\"url\":\"http://wiadomosci.gazeta.pl/wiadomosci/7,114883,24309074,grzyb-z-puszczy-bialowieskiej-nadzieja-w-walce-z-rakiem-naukowcy.html\",\"urlToImage\":\"https://bi.im-g.pl/im/60/2e/17/z24309088IER,Badania-nad-korzeniowcem-sosnowym.jpg\",\"publishedAt\":\"2018-12-25T12:28:00Z\",\"content\":\",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, W dniach 21-24 II 2019 papie ma si w Watykanie spotka z kardynaami, biskupami w sprawie pedofilii ksiy i zakonnikw. Dlatego potrzebna … [+427 chars]\"}]}";

        final String NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_APIKEY_NOT_DEFAULT_PAGE = createURI(newsApiOrgConfig.getNewsApiUrl(),"?",COUNTRY_PL,AND,CATEGORY_SPORTS,AND,PAGE_NOT_DEFAULT,AND,PAGE_SIZE_DEFAULT,AND, QUERY,AND,API_KEY,newsApiOrgConfig.getApiKey().trim());

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_APIKEY_NOT_DEFAULT_PAGE))
                .andRespond(withSuccess(value5, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory(PL, SPORTS, NOT_DEFAULT_PAGE, DEFAULT_PAGE_SIZE,null);

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("28", topHeadline.getTotalResults());
        assertEquals(1, topHeadline.getArticles().length);
    }

    @Test
    public void findByCountryAndCategory_NotDefaultPageSize() {

        String value5 = "{\"status\":\"ok\",\"totalResults\":28,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Gazeta.pl\"},\"author\":\"mk\",\"title\":\"Grzyb z Puszczy Białowieskiej nadzieją w walce z rakiem. Naukowcy złożyli wniosek patentowy - Wiadomosci Gazeta.pl\",\"description\":\"Niepozorny grzyb, korzeniowiec sosnowy wyst�puj�cy m.in. w Puszczy Bia�owieskiej, mo�e pom�c w walce z nowotworem jelita grubego. Naukowcy z Uniwersytetu Medycznego w Bia�ymstoku i Politechniki Bia�ostockiej z�o�yli wniosek patentowy.\",\"url\":\"http://wiadomosci.gazeta.pl/wiadomosci/7,114883,24309074,grzyb-z-puszczy-bialowieskiej-nadzieja-w-walce-z-rakiem-naukowcy.html\",\"urlToImage\":\"https://bi.im-g.pl/im/60/2e/17/z24309088IER,Badania-nad-korzeniowcem-sosnowym.jpg\",\"publishedAt\":\"2018-12-25T12:28:00Z\",\"content\":\",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, W dniach 21-24 II 2019 papie ma si w Watykanie spotka z kardynaami, biskupami w sprawie pedofilii ksiy i zakonnikw. Dlatego potrzebna … [+427 chars]\"}]}";

        final String NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_APIKEY_NOT_DEFAULT_PAGESIZE = createURI(newsApiOrgConfig.getNewsApiUrl(),"?",COUNTRY_PL,AND,CATEGORY_SPORTS,AND,PAGE_DEFAULT,AND,PAGE_SIZE_NOT_DEFAULT,AND, QUERY,AND,API_KEY,newsApiOrgConfig.getApiKey().trim());

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_APIKEY_NOT_DEFAULT_PAGESIZE))
                .andRespond(withSuccess(value5, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory(PL, SPORTS, DEFAULT_PAGE, NOT_DEFAULT_PAGESIZE,null);

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("28", topHeadline.getTotalResults());
        assertEquals(1, topHeadline.getArticles().length);
    }

    @Test
    public void findByCountryAndCategory_withSearchQuery() {

        String value5 = "{\"status\":\"ok\",\"totalResults\":28,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Gazeta.pl\"},\"author\":\"mk\",\"title\":\"Grzyb z Puszczy Białowieskiej nadzieją w walce z rakiem. Naukowcy złożyli wniosek patentowy - Wiadomosci Gazeta.pl\",\"description\":\"Niepozorny grzyb, korzeniowiec sosnowy wyst�puj�cy m.in. w Puszczy Bia�owieskiej, mo�e pom�c w walce z nowotworem jelita grubego. Naukowcy z Uniwersytetu Medycznego w Bia�ymstoku i Politechniki Bia�ostockiej z�o�yli wniosek patentowy.\",\"url\":\"http://wiadomosci.gazeta.pl/wiadomosci/7,114883,24309074,grzyb-z-puszczy-bialowieskiej-nadzieja-w-walce-z-rakiem-naukowcy.html\",\"urlToImage\":\"https://bi.im-g.pl/im/60/2e/17/z24309088IER,Badania-nad-korzeniowcem-sosnowym.jpg\",\"publishedAt\":\"2018-12-25T12:28:00Z\",\"content\":\",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, W dniach 21-24 II 2019 papie ma si w Watykanie spotka z kardynaami, biskupami w sprawie pedofilii ksiy i zakonnikw. Dlatego potrzebna … [+427 chars]\"}]}";

        final String NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_QUERY_TEST_APIKEY = createURI(newsApiOrgConfig.getNewsApiUrl(),"?",COUNTRY_PL,AND,CATEGORY_SPORTS,AND,PAGE_DEFAULT,AND,PAGE_SIZE_DEFAULT,AND, QUERY_TEST,AND,API_KEY,newsApiOrgConfig.getApiKey().trim());

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_QUERY_TEST_APIKEY))
                .andRespond(withSuccess(value5, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory(PL, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE,TEST);

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("28", topHeadline.getTotalResults());
        assertEquals(1, topHeadline.getArticles().length);
    }

    @Test
    public void findByCountryAndCategory_searchQueryEmpty() {

        String value5 = "{\"status\":\"ok\",\"totalResults\":28,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Gazeta.pl\"},\"author\":\"mk\",\"title\":\"Grzyb z Puszczy Białowieskiej nadzieją w walce z rakiem. Naukowcy złożyli wniosek patentowy - Wiadomosci Gazeta.pl\",\"description\":\"Niepozorny grzyb, korzeniowiec sosnowy wyst�puj�cy m.in. w Puszczy Bia�owieskiej, mo�e pom�c w walce z nowotworem jelita grubego. Naukowcy z Uniwersytetu Medycznego w Bia�ymstoku i Politechniki Bia�ostockiej z�o�yli wniosek patentowy.\",\"url\":\"http://wiadomosci.gazeta.pl/wiadomosci/7,114883,24309074,grzyb-z-puszczy-bialowieskiej-nadzieja-w-walce-z-rakiem-naukowcy.html\",\"urlToImage\":\"https://bi.im-g.pl/im/60/2e/17/z24309088IER,Badania-nad-korzeniowcem-sosnowym.jpg\",\"publishedAt\":\"2018-12-25T12:28:00Z\",\"content\":\",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, W dniach 21-24 II 2019 papie ma si w Watykanie spotka z kardynaami, biskupami w sprawie pedofilii ksiy i zakonnikw. Dlatego potrzebna … [+427 chars]\"}]}";

        final String NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_QUERY_EMPTY_APIKEY = createURI(newsApiOrgConfig.getNewsApiUrl(),"?",COUNTRY_PL,AND,CATEGORY_SPORTS,AND,PAGE_DEFAULT,AND,PAGE_SIZE_DEFAULT,AND, QUER_EMPTY,AND,API_KEY,newsApiOrgConfig.getApiKey().trim());

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_PL_SPORTS_QUERY_EMPTY_APIKEY))
                .andRespond(withSuccess(value5, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory(PL, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE,"");

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("28", topHeadline.getTotalResults());
        assertEquals(1, topHeadline.getArticles().length);
    }


    private static String createURI(String... strings) {
        StringBuffer stringBuffer = new StringBuffer();

        Stream.of(strings).forEach(s -> stringBuffer.append(s));

        return stringBuffer.toString();
    }

}