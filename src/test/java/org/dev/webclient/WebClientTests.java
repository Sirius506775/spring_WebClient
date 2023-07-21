package org.dev.webclient;

import org.dev.webclient.domain.Motion;
import org.dev.webclient.util.WebClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class WebClientTests {

    @Test
    void getBaseUrl() {
        WebClient webClient = WebClientUtil.getBaseUrl("http://localhost:8080"); //WebClient 객체를 생성
        List<Motion> motions = webClient.get()
                .uri("/server/getMotionData" + "?programName=" + "외전")
                .retrieve() //HTTP 응답을 비동기 수신
                .bodyToFlux(Motion.class) //응답 본문을 Motion 객체의 Flux로 변환
                .collectList()// Flux를 List로 변환
                .block();
        System.out.println(motions.toString());
    }
}
