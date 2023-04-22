package org.example.simple.client;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;

import java.nio.charset.StandardCharsets;

public class VertxClient {
    public static void main(String[] args) {
        HttpClient httpClient = Vertx.vertx().createHttpClient();
        httpClient.request(HttpMethod.GET,8080,"localhost","/aaa",handler->{
            if(handler.succeeded()){
                handler.result().end("hello,Server! I am Client");
//                handler.result().response().result().handler(ha->{
//                    System.out.println(ha.toString(StandardCharsets.UTF_8));
//                });
                Future<HttpClientResponse> httpClientResponseFuture = handler.result().response().onComplete(compleHandler -> {
                    compleHandler.result().body(han -> {
                        String s = han.result().toString(StandardCharsets.UTF_8);
                        System.out.println(s + ", client received");
                    });
                });
            }else {
                System.out.println("exception");
            }
        });
        httpClient.close();
    }
}
