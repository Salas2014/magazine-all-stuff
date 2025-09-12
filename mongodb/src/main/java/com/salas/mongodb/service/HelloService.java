package com.salas.mongodb.service;

import com.salas.mongodb.GreeterGrpc;
import com.salas.mongodb.HelloWorldProto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloWorldProto.HelloRequest request,
                         StreamObserver<HelloWorldProto.HelloReply> responseObserver) {

        String message = "Hello " + request.getName() + " " + request.getLastName() + "! was modifier by server";
        HelloWorldProto.HelloReply reply = HelloWorldProto.HelloReply
                .newBuilder()
                .setMessage(message).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
