package me.totalfreedom.obsidian.http;

import java.awt.image.DataBuffer;
import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WebRequest {
    WebMethod getMethod();
    String getUri();
    WebHeaders getHeaders();
    Mono<ByteBuffer> getBody();
    Flux<DataBuffer> getBodyFlux();
}

