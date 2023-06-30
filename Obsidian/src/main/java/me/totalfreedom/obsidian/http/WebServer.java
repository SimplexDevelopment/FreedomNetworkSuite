package me.totalfreedom.obsidian.http;

public interface WebServer {
    void start();
    void stop();
    void registerHandler(String uri, WebHandler handler);
    void unregisterHandler(String uri);
}

