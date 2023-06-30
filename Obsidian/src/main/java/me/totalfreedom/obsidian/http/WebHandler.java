package me.totalfreedom.obsidian.http;

import reactor.core.publisher.Mono;

public interface WebHandler {
    Mono<WebResponse> handleRequest(WebRequest request);
}

