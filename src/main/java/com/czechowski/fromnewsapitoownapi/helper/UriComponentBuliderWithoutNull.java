package com.czechowski.fromnewsapitoownapi.helper;

import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class UriComponentBuliderWithoutNull {

    private static BuliderWithoutNull buliderWithoutNull;

    public static class BuliderWithoutNull {

         UriComponentsBuilder uriComponentsBuilder;

        public  BuliderWithoutNull queryParam(String name, String string) {

            if (!StringUtils.isEmpty(string)) {
                uriComponentsBuilder.queryParam(name, string);
            }
            return this;
        }

        public BuliderWithoutNull queryParam(String name, int number) {
            uriComponentsBuilder.queryParam(name, number);

            return this;
        }

        public BuliderWithoutNull encode() {
            uriComponentsBuilder.encode();
            return this;
        }

        public String toUriString() {
            return uriComponentsBuilder.toUriString();
        }
    }

    public static BuliderWithoutNull fromHttpUrl(String httpUrl) {

        buliderWithoutNull = new BuliderWithoutNull();

        buliderWithoutNull.uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);

        return buliderWithoutNull;
    }

}
