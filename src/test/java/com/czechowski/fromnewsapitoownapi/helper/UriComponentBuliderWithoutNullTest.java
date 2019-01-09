package com.czechowski.fromnewsapitoownapi.helper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class UriComponentBuliderWithoutNullTest {

    private final String GOOGLE = "http://google.com";
    private final String PARAM = "param";
    private final String PARAM_VALUE = "value";
    private final String PARAM_VALUE_EMPTY = "";
    private final String PARAM_VALUE_NULL = null;
    private final int PARAM_VALUE_INT = 1;


    @Test
    public void fromHttpUrl() {

        String actual = UriComponentBuliderWithoutNull.fromHttpUrl(GOOGLE).toUriString();

        assertNotNull(actual);
        assertEquals(GOOGLE, actual);

    }

    @Test
    public void fromHttpUrlWithStringParam() {

        String actual = UriComponentBuliderWithoutNull.fromHttpUrl(GOOGLE)
                .queryParam(PARAM, PARAM_VALUE).toUriString();

        String expected = GOOGLE + "?" + PARAM + "=" + PARAM_VALUE;

        assertNotNull(actual);
        assertEquals(expected, actual);

    }

    @Test
    public void fromHttpUrlWithEmptyStringParam() {

        String actual = UriComponentBuliderWithoutNull.fromHttpUrl(GOOGLE).queryParam(PARAM, PARAM_VALUE_EMPTY).toUriString();

        assertNotNull(actual);
        assertEquals(GOOGLE, actual);

    }

    @Test
    public void fromHttpUrlWithNullParam() {

        String actual = UriComponentBuliderWithoutNull.fromHttpUrl(GOOGLE).queryParam(PARAM, PARAM_VALUE_NULL).toUriString();

        assertNotNull(actual);
        assertEquals(GOOGLE, actual);

    }

    @Test
    public void fromHttpUrlWithIntParam() {

        String actual = UriComponentBuliderWithoutNull.fromHttpUrl(GOOGLE).queryParam(PARAM, PARAM_VALUE_INT).toUriString();

        String expected = GOOGLE + "?" + PARAM + "=" + PARAM_VALUE_INT;

        assertNotNull(actual);
        assertEquals(expected, actual);

    }
}