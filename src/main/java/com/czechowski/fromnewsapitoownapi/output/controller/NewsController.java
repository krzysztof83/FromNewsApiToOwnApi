package com.czechowski.fromnewsapitoownapi.output.controller;

import com.czechowski.fromnewsapitoownapi.helper.UriComponentBuliderWithoutNull;
import com.czechowski.fromnewsapitoownapi.output.decorator.NewsDecorator;
import com.czechowski.fromnewsapitoownapi.output.model.News;
import com.czechowski.fromnewsapitoownapi.output.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{country}/{category}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<News> getNewsByCountryAndCategory(@PathVariable(name = "country") String country,
                                                            @PathVariable(name = "category") String category,
                                                            @RequestParam(value = "page", defaultValue = "0") String pageInString,
                                                            @RequestParam(value = "pageSize", defaultValue = "20") String pageSizeInString,
                                                            @RequestParam(value = "q", defaultValue = "") String queryToSearch, HttpServletRequest request) {

        NewsDecorator newsDecorator = newsService.findByCountryAndCategory(country, category, pageInString, pageSizeInString, queryToSearch);

        HttpHeaders responseHeaders = new HttpHeaders();

        int page = Integer.valueOf(pageInString);
        int pageSize = Integer.valueOf(pageSizeInString);

        addNavigationLinksToHeadder(responseHeaders, newsDecorator.getTotalResults(), page, pageSize, request.getRequestURL().toString(), queryToSearch);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(newsDecorator);

    }

    private void addNavigationLinksToHeadder(HttpHeaders responseHeaders, int totalResults, int page, int pageSize, String requestUrl, String queryToSearch) {

        boolean hasManyPage = pageSize < totalResults;

        if (hasManyPage) {

            int amountPage = (totalResults / pageSize) + 1;

            boolean hasNext = page < amountPage;

            boolean hasPrev = page > 0;

            if (hasNext) {
                String next = createLinks(page + 1, pageSize, requestUrl, queryToSearch);
                String last = createLinks(amountPage, pageSize, requestUrl, queryToSearch);
                responseHeaders.set("next", next);
                responseHeaders.set("last", last);
            }

            if (hasPrev) {
                String first = createLinks(0, pageSize, requestUrl, queryToSearch);
                String prev = createLinks(page - 1, pageSize, requestUrl, queryToSearch);
                responseHeaders.set("first", first);
                responseHeaders.set("prev", prev);

            }
        }

    }

    private String createLinks(int page, int pageSize, String requestUrl, String queryToSearch) {

        return UriComponentBuliderWithoutNull.fromHttpUrl(requestUrl)
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("q", queryToSearch)
                .encode().toUriString();
    }

}
