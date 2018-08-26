package io.techack.examples.routeguide.endpoint;

import io.grpc.stub.StreamObserver;
import io.techack.examples.routeguide.rpc.hw.GreeterGrpc;
import io.techack.examples.routeguide.rpc.hw.HelloReply;
import io.techack.examples.routeguide.rpc.hw.HelloRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreeterEndpoint extends GreeterGrpc.GreeterImplBase {

    final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        LOGGER.info("request => {}", request.getName());
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("hello " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<HelloRequest> routeChat(StreamObserver<HelloReply> responseObserver) {
        return super.routeChat(responseObserver);
    }
}
