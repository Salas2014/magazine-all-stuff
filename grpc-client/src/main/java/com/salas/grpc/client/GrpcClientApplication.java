package com.salas.grpc.client;

import com.salas.grpc.client.service.GrpcClientService;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(GrpcClientApplication.class, args);

        GrpcClientService grpcClientService = context.getBean(GrpcClientService.class);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String name = scanner.nextLine();
            String lastName = scanner.nextLine();

            String greet = grpcClientService.greet(name, lastName);
            System.out.println(greet);
        }
    }

}
