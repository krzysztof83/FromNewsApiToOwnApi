package com.czechowski.fromnewsapitoownapi.input.service;

import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@RunWith(SpringRunner.class)
@RestClientTest(TopHeadlinesServiceImpl.class)
public class TopHeadlineServiceTest {

    public static final String NEWSAPI_V2_TOPHEDLINES_PL_SPORTS_APIKEY = "https://newsapi.org/v2/top-headlines?country=pl&category=sports&apiKey=c35f39498cda43069cdd4f3c24c4740a";
    public static final String NEWSAPI_V2_TOPHEADLINES_NULL_SPORTS_APIKEY = "https://newsapi.org/v2/top-headlines?country&category=sports&apiKey=c35f39498cda43069cdd4f3c24c4740a";
    public static final String NEWSAPI_V2_TOPHEDLINES_COUNTRY_NOT_CORRECT_SPORTS_APIKEY = "https://newsapi.org/v2/top-headlines?country=asd&category=sports&apiKey=c35f39498cda43069cdd4f3c24c4740a";

    public static final String NEWSAPI_V2_TOPHEDLINES_PL_NULL_APIKEY = "https://newsapi.org/v2/top-headlines?country=pl&category&apiKey=c35f39498cda43069cdd4f3c24c4740a";
    public static final String NEWSAPI_V2_TOPHEDLINES_PL_CATEGORY_NOT_CORRECT_APIKEY = "https://newsapi.org/v2/top-headlines?country=pl&category=asd&apiKey=c35f39498cda43069cdd4f3c24c4740a";

    @Autowired
    private TopHeadlineService topHeadlineService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void findByCountryAndCategoryTest() {

        String value = "{\"status\":\"ok\",\"totalResults\":\"1\",\"articles\":[{\"source\":{\"id\":null,\"name\":\"Probasket.pl\"},\"author\":null,\"title\":\"NBA: Gortat narzeka na Jokicia - probasket.pl\",\"description\":\"Marcin Gortat docenia talent lidera Denver Nuggets, ale wkurza go jedna rzecz, mianowicie skłonność Nikoli Jokicia do flopowania. Co na to zawodnik Bryłek? Nie\",\"url\":\"http://probasket.pl/nba-gortat-narzeka-na-jokicia/\",\"urlToImage\":\"http://probasket.pl/wp-content/uploads/2018/12/maxresdefault-51.jpg\",\"publishedAt\":\"2018-12-24T10:27:00Z\",\"content\":\"Marcin Gortat docenia talent lidera Denver Nuggets, ale wkurza go jedna rzecz, mianowicie skonno Nikoli Jokicia do flopowania. Co na to zawodnik Bryek? Nie mia adnego konkretnego komentarza do sów rodkowego Los Angeles Clippers. Nikola Joki po dwóch faulach t… [+1085 chars]\"}]}";

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEDLINES_PL_SPORTS_APIKEY))
                .andRespond(withSuccess(value, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory("pl", "sports");

        server.verify();
        assertEquals("ok",topHeadline.getStatus());
        assertEquals("1",topHeadline.getTotalResults());
        assertEquals(1, topHeadline.getArticles().length);
    }

    @Test
    public void findByCountryAndCategory_CountryNull() {

        String value2 = "{\"status\":\"ok\",\"totalResults\":3403,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Mobil.nsd.se\"},\"author\":\"Carl Göransson/TT\",\"title\":\"Kramer blickar framåt efter succésilvret - NSD\",\"description\":\"Friidrott Kungen av genombrott nöjer sig inte med EM-silvret från i somras.Nu laddar Andreas Kramer om för en säsong med trippla mästerskap.Jag känner att det kan bli ett väldigt bra år för mig, säger 800-meterslöparen.\",\"url\":\"https://mobil.nsd.se/sport/kramer-blickar-framat-efter-succsilvret-nm4993444.aspx\",\"urlToImage\":\"https://img8.ntm.eu/nm/public/img/4993447/1225141008/kramer-tranar-infor-ett-hektis?w=980&h=551&mode=crop&scale=both&anchor=topcenter\",\"publishedAt\":\"2018-12-25T13:16:55Z\",\"content\":\"Carl Göransson/TT 14:07 | 2018-12-25 21-årige Andreas Kramers utveckling de senaste åren illustreras kanske bäst av nomineringarna till Svenska friidrottsförbundets årliga gala. 2016 var han nominerad till årets genombrott i landslaget. Samma visa 2017. Och i… [+3118 chars]\"}]}";

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEADLINES_NULL_SPORTS_APIKEY))
                .andRespond(withSuccess(value2, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory(null, "sports");

        server.verify();
        assertEquals("ok",topHeadline.getStatus());
        assertEquals("3403",topHeadline.getTotalResults());
        assertEquals(1,topHeadline.getArticles().length );
    }

    @Test
    public void findByCountryAndCategory_CountryNotCorrect() {

        String value3 = "{\"status\":\"ok\",\"totalResults\":0,\"articles\":[]}";

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEDLINES_COUNTRY_NOT_CORRECT_SPORTS_APIKEY))
                .andRespond(withSuccess(value3, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory("asd", "sports");

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("0", topHeadline.getTotalResults());
        assertEquals(0, topHeadline.getArticles().length);
    }

    @Test
    public void findByCountryAndCategoryCategoryNull() {

        String value4 = "{\"status\":\"ok\",\"totalResults\":28,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Gazeta.pl\"},\"author\":\"mk\",\"title\":\"Grzyb z Puszczy Białowieskiej nadzieją w walce z rakiem. Naukowcy złożyli wniosek patentowy - Wiadomosci Gazeta.pl\",\"description\":\"Niepozorny grzyb, korzeniowiec sosnowy wyst�puj�cy m.in. w Puszczy Bia�owieskiej, mo�e pom�c w walce z nowotworem jelita grubego. Naukowcy z Uniwersytetu Medycznego w Bia�ymstoku i Politechniki Bia�ostockiej z�o�yli wniosek patentowy.\",\"url\":\"http://wiadomosci.gazeta.pl/wiadomosci/7,114883,24309074,grzyb-z-puszczy-bialowieskiej-nadzieja-w-walce-z-rakiem-naukowcy.html\",\"urlToImage\":\"https://bi.im-g.pl/im/60/2e/17/z24309088IER,Badania-nad-korzeniowcem-sosnowym.jpg\",\"publishedAt\":\"2018-12-25T12:28:00Z\",\"content\":\",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, W dniach 21-24 II 2019 papie ma si w Watykanie spotka z kardynaami, biskupami w sprawie pedofilii ksiy i zakonnikw. Dlatego potrzebna … [+427 chars]\"}]}";

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEDLINES_PL_NULL_APIKEY))
                .andRespond(withSuccess(value4, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory("pl", null);

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("28", topHeadline.getTotalResults());
        assertEquals(1, topHeadline.getArticles().length);

    }

    @Test
    public void findByCountryAndCategory_CategoryNotCorrect() {

        String value5 = "{\"status\":\"ok\",\"totalResults\":0,\"articles\":[]}";

        this.server.expect(requestTo(NEWSAPI_V2_TOPHEDLINES_PL_CATEGORY_NOT_CORRECT_APIKEY))
                .andRespond(withSuccess(value5, MediaType.APPLICATION_JSON));

        TopHeadline topHeadline = this.topHeadlineService.findByCountryAndCategory("pl", "asd");

        server.verify();
        assertEquals("ok", topHeadline.getStatus());
        assertEquals("0", topHeadline.getTotalResults());
        assertEquals(0, topHeadline.getArticles().length);
    }

}