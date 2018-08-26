package io.techack.examples.routeguide.verticle;

import io.vertx.core.Vertx;

public class VerticleRunner {

    public static void main(String[] args) throws Exception {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(ServerVerticle.class.getName());
    }
}
