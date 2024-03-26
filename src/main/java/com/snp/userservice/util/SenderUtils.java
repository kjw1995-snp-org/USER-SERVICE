package com.snp.userservice.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Consumer;

@Component
@Slf4j
public class SenderUtils {

    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * 서버 통신
     * @param host
     * @param uri
     * @param method
     * @param contentType
     * @param acceptType
     * @param data
     * @param responseClass
     * @return T
     * @param <T>
     */
    public <T> T send(String host, String uri, HttpMethod method, Map<String, String> headers, MediaType contentType, MediaType acceptType, Object data, ParameterizedTypeReference<T> responseClass) {


        return retrieve(getWebClient(), host, uri, headers, method, contentType, acceptType, data, responseClass);
    }

    /**
     * 통신 Spec 설정
     * @param webClient
     * @param host
     * @param uri
     * @param method
     * @param contentType
     * @param acceptType
     * @param data
     * @param responseClass
     * @return T
     * @param <T>
     */
    private <T> T retrieve(WebClient webClient, String host, String uri, Map<String, String> headers, HttpMethod method, MediaType contentType, MediaType acceptType, Object data, ParameterizedTypeReference<T> responseClass) {

        WebClient.RequestHeadersSpec<?> req = webClient.method(method)
                .uri(host + uri)
                .contentType(contentType)
                .accept(acceptType)
                .bodyValue(data == null ? "" : data);

        if(headers != null) {
            req.headers(createHeaders(headers));
        }

        Mono<T> mono = req.retrieve().bodyToMono(responseClass);
        T response = mono.block();
        log.info("Response Data = {}", response);

        return response;
    }

    /**
     * 서버 통신을 위한 WebClient 생성, 스레드 관리 및 리소스를 고려한 동기화(synchronized) 설정
     * @return {@link WebClient}
     */
    private synchronized WebClient getWebClient() {
        return webClientBuilder.build();
    }

    private Consumer<HttpHeaders> createHeaders(Map<String, String> headers) {

        Consumer<HttpHeaders> header = new Consumer<HttpHeaders>() {
            @Override
            public void accept(HttpHeaders httpHeaders) {
                for(String key : headers.keySet()) {
                    httpHeaders.add(key, headers.get(key));
                }
            }
        };

        return header;

    }

}
