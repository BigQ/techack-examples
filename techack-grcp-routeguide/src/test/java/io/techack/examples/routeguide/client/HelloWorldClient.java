package io.techack.examples.routeguide.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.techack.examples.routeguide.rpc.hw.GreeterGrpc;
import io.techack.examples.routeguide.rpc.hw.HelloReply;
import io.techack.examples.routeguide.rpc.hw.HelloRequest;

import java.util.concurrent.CountDownLatch;

public class HelloWorldClient {

    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext(true)
                .build();
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);
        HelloRequest request = HelloRequest.newBuilder()
                .setName("Tina")
                .build();
        final CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<HelloReply> ob = new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply helloReply) {
                System.out.println(helloReply);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        };
        stub.sayHello(request, ob);
        latch.await();
    }
}
