package org.dev.webclient.controller;

import lombok.extern.log4j.Log4j2;
import org.dev.webclient.domain.Motion;
import org.dev.webclient.util.WebClientUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/server")
@Log4j2
public class SampleController {
    private static String URL = "http://localhost:8080/model";

    @GetMapping("/motion")
    public List<Motion> getMotion(@RequestParam String programName) {
        // flux 만들기 (연결 정보를 담은)
        Flux<Motion> flux = WebClientUtil.getBaseUrl(URL)
                .get()
                .uri(builder -> builder.path("/getMotionData")
                        .queryParam("programName", programName)
                        .build())
                .retrieve()
                .bodyToFlux(Motion.class);

        log.info("----- API Server에서 Model Server에 데이터를 호출 -----");

        // 실제 motions 받아오기
        List<Motion> motions = flux.toStream().collect(Collectors.toList());
        return motions;
    }

    @GetMapping("/enter")
    public String enterModel(@RequestParam boolean block, @RequestParam int request) {
        long requestTime = System.currentTimeMillis(); //model api 최초 호출 시간

        connectModelServer(block, request); //연결 시도

        long closeTime = System.currentTimeMillis();  // 호출 종료 시간 측정
        return (double) (closeTime - requestTime) / 1000 + " seconds";
    }

    private static void connectModelServer(boolean block, int people) {
        int n = 0;
        while (people-- > 0) {
            Mono<String> mono = WebClient.create()
                    .get()
                    .uri(URL + "/connect")
                    .retrieve()
                    .bodyToMono(String.class);

            if (block) {
                System.out.println(mono.block());
            } else {
                mono.subscribe(System.out::println);
            }
        }
    }


}
