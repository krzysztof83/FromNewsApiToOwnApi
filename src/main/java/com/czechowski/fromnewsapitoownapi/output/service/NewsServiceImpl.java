package com.czechowski.fromnewsapitoownapi.output.service;

import com.czechowski.fromnewsapitoownapi.input.model.TopHeadline;
import com.czechowski.fromnewsapitoownapi.input.service.TopHeadlineService;
import com.czechowski.fromnewsapitoownapi.output.converter.TopHeadLineToNewsConverter;
import com.czechowski.fromnewsapitoownapi.output.model.News;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@Service
public class NewsServiceImpl implements NewsService {

    private TopHeadlineService topHeadlineService;
    private TopHeadLineToNewsConverter topHeadLineToNewsConverter;

    public NewsServiceImpl(TopHeadlineService topHeadlineService, TopHeadLineToNewsConverter topHeadLineToNewsConverter) {
        this.topHeadlineService = topHeadlineService;
        this.topHeadLineToNewsConverter = topHeadLineToNewsConverter;
    }

    public News findByCountryAndCategory(String country, String category, String pageInString, String pageSizeInString)  {

        StrategyWithUnsupported.handleUnsupported(country, category, pageInString, pageSizeInString);

        int page = Integer.valueOf(pageInString);
        int pageSize = Integer.valueOf(pageSizeInString);

        TopHeadline topHeadline = topHeadlineService.findByCountryAndCategory(country, category, page, pageSize);

        return topHeadLineToNewsConverter.convert(topHeadline, country, category);
    }
}
