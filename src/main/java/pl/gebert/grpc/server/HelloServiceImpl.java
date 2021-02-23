package pl.gebert.grpc.server;

import io.grpc.stub.StreamObserver;
import pl.gebert.grpc.HelloRequest;
import pl.gebert.grpc.HelloResponse;
import pl.gebert.grpc.HelloServiceGrpc.HelloServiceImplBase;

public class HelloServiceImpl extends HelloServiceImplBase {

  @Override
  public void hello(final HelloRequest request,
      final StreamObserver<HelloResponse> responseObserver) {

    String greeting = new StringBuilder()
        .append("Hello, ")
        .append(request.getFirstName())
        .append(" ")
        .append(request.getLastName())
        .toString();

    HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
