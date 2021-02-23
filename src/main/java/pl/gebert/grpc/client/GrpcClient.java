package pl.gebert.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.logging.Logger;
import pl.gebert.grpc.HelloRequest;
import pl.gebert.grpc.HelloResponse;
import pl.gebert.grpc.HelloServiceGrpc;

public class GrpcClient {

  private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());

  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();

    HelloServiceGrpc.HelloServiceBlockingStub stub
        = HelloServiceGrpc.newBlockingStub(channel);

    HelloRequest request = HelloRequest.newBuilder()
        .setFirstName("John")
        .setLastName("Doe")
        .build();
    HelloResponse helloResponse = stub.hello(request);

    logger.info(helloResponse.getGreeting());

    channel.shutdown();
  }
}
