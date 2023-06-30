package me.totalfreedom.obsidian.http;

import reactor.core.publisher.Mono;

public interface WebClient {
    Mono<WebResponse> sendRequest(WebRequest request);
}

