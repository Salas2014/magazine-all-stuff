package com.salas.grpc.client.service;

import com.salas.mongodb.GreeterGrpc;
import com.salas.mongodb.HelloWorldProto;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {


    @GrpcClient("myGrpcService")
    private GreeterGrpc.GreeterBlockingStub blockingStub;

    public String greet(String name, String lastName) {
        HelloWorldProto.HelloRequest request = HelloWorldProto.HelloRequest.newBuilder()
                .setName(name)
                .setLastName(lastName)
                .build();

       return blockingStub.sayHello(request).getMessage();
    }
}
