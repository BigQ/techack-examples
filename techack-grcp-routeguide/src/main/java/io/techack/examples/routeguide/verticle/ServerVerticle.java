package io.techack.examples.routeguide.verticle;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.techack.examples.routeguide.endpoint.GreeterEndpoint;
import io.vertx.core.AbstractVerticle;

public class ServerVerticle extends AbstractVerticle {

    protected Server server;

    @Override
    public void start() throws Exception {
        server = ServerBuilder.forPort(8080)
                .addService(new GreeterEndpoint())
                .build();
        server.start();
    }

    @Override
    public void stop() throws Exception {
        if (server != null) {
            server.shutdownNow();
        }
    }
}
