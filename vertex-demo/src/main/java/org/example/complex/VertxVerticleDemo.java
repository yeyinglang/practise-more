package org.example.complex;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import java.nio.charset.StandardCharsets;

public class VertxVerticleDemo extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        System.out.println("Java Configuration: " + config().getString("name"));
        vertx.createHttpServer().requestHandler(req -> {
            System.out.println("received request, "+ req.bodyHandler(handler->{
                System.out.println(handler.toString(StandardCharsets.UTF_8));
            }));
            req.response().putHeader("content-type", "text/plain")
                    .end("hello,I am Server");

        }).listen(8080, http->{
            if(http.succeeded()) {
                startPromise.complete();
                System.out.println("start on http://localhost:8080");
            }else {
                startPromise.fail("failed msg");
            }
        });


    }
}
