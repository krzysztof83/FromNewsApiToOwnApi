package com.czechowski.fromnewsapitoownapi.output.decorator;

import com.czechowski.fromnewsapitoownapi.output.model.News;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public interface NewsDecorator extends News {

    int getTotalResults();

}
