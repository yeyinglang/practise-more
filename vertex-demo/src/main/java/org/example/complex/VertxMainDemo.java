package org.example.complex;

import io.vertx.core.Vertx;

public class VertxMainDemo {
    /**
     * @param args
     */
    public static void main(String[] args) {
//        JsonObject jsonObject = new JsonObject().put("name", "tim").put("directory", "/aaac");
//        DeploymentOptions deploymentOptions = new DeploymentOptions(jsonObject);

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle("org.example.complex.VertxVerticleDemo", res->{
            if(res.succeeded()){
                System.out.println("deploy success, result = " + res.result());
            }else {
                System.out.println(res.cause());
            }
        });
    }
}
