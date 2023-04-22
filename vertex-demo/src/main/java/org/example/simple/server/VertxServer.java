package org.example.simple.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

import java.nio.charset.StandardCharsets;

public class VertxServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(handler->{
            handler.bodyHandler(bodyhandler->{
                String s = bodyhandler.toString(StandardCharsets.UTF_8);
                System.out.println("body res: " + s);
            });
            HttpServerResponse response = handler.response();
            response.end("hello clien, I am server");
            response.close();
        });
        httpServer.listen(8080, "localhost");
    }
}
