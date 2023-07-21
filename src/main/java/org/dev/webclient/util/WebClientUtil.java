package org.dev.webclient.util;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientUtil {
    public static WebClient getBaseUrl(final String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
