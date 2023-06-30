package me.totalfreedom.obsidian.http;

import java.awt.image.DataBuffer;
import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WebResponse {
    WebStatus getStatus();
    WebHeaders getHeaders();
    void setStatus(WebStatus status);
    void setHeader(String name, String value);
    Mono<Void> write(ByteBuffer data);
    Mono<Void> write(DataBuffer dataBuffer);
    Mono<Void> write(Flux<DataBuffer> dataFlux);
}

