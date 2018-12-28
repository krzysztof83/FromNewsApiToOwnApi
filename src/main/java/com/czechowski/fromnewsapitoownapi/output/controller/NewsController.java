package com.czechowski.fromnewsapitoownapi.output.controller;

import com.czechowski.fromnewsapitoownapi.output.model.News;
import com.czechowski.fromnewsapitoownapi.output.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public News getNewsByCountryAndCategory(@PathVariable(name = "country") String country, @PathVariable(name = "category") String category, @RequestParam(value = "page", defaultValue = "0") String pageInString,
                                            @RequestParam(value = "pageSize", defaultValue = "20") String pageSizeInString) {



        return newsService.findByCountryAndCategory(country, category, pageInString, pageSizeInString);
    }

}
