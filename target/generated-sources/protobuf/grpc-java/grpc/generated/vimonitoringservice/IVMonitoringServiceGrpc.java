package grpc.generated.vimonitoringservice;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * monitoring service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: IVMonitoringService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class IVMonitoringServiceGrpc {

  private IVMonitoringServiceGrpc() {}

  public static final String SERVICE_NAME = "smart_healthcare.IVMonitoringService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.vimonitoringservice.IVStatusRequest,
      grpc.generated.vimonitoringservice.IVStatusResponse> getGetIVStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetIVStatus",
      requestType = grpc.generated.vimonitoringservice.IVStatusRequest.class,
      responseType = grpc.generated.vimonitoringservice.IVStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.generated.vimonitoringservice.IVStatusRequest,
      grpc.generated.vimonitoringservice.IVStatusResponse> getGetIVStatusMethod() {
    io.grpc.MethodDescriptor<grpc.generated.vimonitoringservice.IVStatusRequest, grpc.generated.vimonitoringservice.IVStatusResponse> getGetIVStatusMethod;
    if ((getGetIVStatusMethod = IVMonitoringServiceGrpc.getGetIVStatusMethod) == null) {
      synchronized (IVMonitoringServiceGrpc.class) {
        if ((getGetIVStatusMethod = IVMonitoringServiceGrpc.getGetIVStatusMethod) == null) {
          IVMonitoringServiceGrpc.getGetIVStatusMethod = getGetIVStatusMethod =
              io.grpc.MethodDescriptor.<grpc.generated.vimonitoringservice.IVStatusRequest, grpc.generated.vimonitoringservice.IVStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetIVStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.vimonitoringservice.IVStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.vimonitoringservice.IVStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new IVMonitoringServiceMethodDescriptorSupplier("GetIVStatus"))
              .build();
        }
      }
    }
    return getGetIVStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.generated.vimonitoringservice.RequestAllStatus,
      grpc.generated.vimonitoringservice.IVStatusResponse> getStreamAllIVStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamAllIVStatus",
      requestType = grpc.generated.vimonitoringservice.RequestAllStatus.class,
      responseType = grpc.generated.vimonitoringservice.IVStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.vimonitoringservice.RequestAllStatus,
      grpc.generated.vimonitoringservice.IVStatusResponse> getStreamAllIVStatusMethod() {
    io.grpc.MethodDescriptor<grpc.generated.vimonitoringservice.RequestAllStatus, grpc.generated.vimonitoringservice.IVStatusResponse> getStreamAllIVStatusMethod;
    if ((getStreamAllIVStatusMethod = IVMonitoringServiceGrpc.getStreamAllIVStatusMethod) == null) {
      synchronized (IVMonitoringServiceGrpc.class) {
        if ((getStreamAllIVStatusMethod = IVMonitoringServiceGrpc.getStreamAllIVStatusMethod) == null) {
          IVMonitoringServiceGrpc.getStreamAllIVStatusMethod = getStreamAllIVStatusMethod =
              io.grpc.MethodDescriptor.<grpc.generated.vimonitoringservice.RequestAllStatus, grpc.generated.vimonitoringservice.IVStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamAllIVStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.vimonitoringservice.RequestAllStatus.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.vimonitoringservice.IVStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new IVMonitoringServiceMethodDescriptorSupplier("StreamAllIVStatus"))
              .build();
        }
      }
    }
    return getStreamAllIVStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IVMonitoringServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IVMonitoringServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IVMonitoringServiceStub>() {
        @java.lang.Override
        public IVMonitoringServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IVMonitoringServiceStub(channel, callOptions);
        }
      };
    return IVMonitoringServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IVMonitoringServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IVMonitoringServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IVMonitoringServiceBlockingStub>() {
        @java.lang.Override
        public IVMonitoringServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IVMonitoringServiceBlockingStub(channel, callOptions);
        }
      };
    return IVMonitoringServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IVMonitoringServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IVMonitoringServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IVMonitoringServiceFutureStub>() {
        @java.lang.Override
        public IVMonitoringServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IVMonitoringServiceFutureStub(channel, callOptions);
        }
      };
    return IVMonitoringServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * monitoring service
   * </pre>
   */
  public static abstract class IVMonitoringServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 1. fetch and monitor patient's drip condition (normal too fast or too slow)
     * Unary
     * </pre>
     */
    public void getIVStatus(grpc.generated.vimonitoringservice.IVStatusRequest request,
        io.grpc.stub.StreamObserver<grpc.generated.vimonitoringservice.IVStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetIVStatusMethod(), responseObserver);
    }

    /**
     * <pre>
     * 2. monitor the infusion status
     * server streaming
     * </pre>
     */
    public void streamAllIVStatus(grpc.generated.vimonitoringservice.RequestAllStatus request,
        io.grpc.stub.StreamObserver<grpc.generated.vimonitoringservice.IVStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamAllIVStatusMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetIVStatusMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                grpc.generated.vimonitoringservice.IVStatusRequest,
                grpc.generated.vimonitoringservice.IVStatusResponse>(
                  this, METHODID_GET_IVSTATUS)))
          .addMethod(
            getStreamAllIVStatusMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                grpc.generated.vimonitoringservice.RequestAllStatus,
                grpc.generated.vimonitoringservice.IVStatusResponse>(
                  this, METHODID_STREAM_ALL_IVSTATUS)))
          .build();
    }
  }

  /**
   * <pre>
   * monitoring service
   * </pre>
   */
  public static final class IVMonitoringServiceStub extends io.grpc.stub.AbstractAsyncStub<IVMonitoringServiceStub> {
    private IVMonitoringServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IVMonitoringServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IVMonitoringServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. fetch and monitor patient's drip condition (normal too fast or too slow)
     * Unary
     * </pre>
     */
    public void getIVStatus(grpc.generated.vimonitoringservice.IVStatusRequest request,
        io.grpc.stub.StreamObserver<grpc.generated.vimonitoringservice.IVStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetIVStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 2. monitor the infusion status
     * server streaming
     * </pre>
     */
    public void streamAllIVStatus(grpc.generated.vimonitoringservice.RequestAllStatus request,
        io.grpc.stub.StreamObserver<grpc.generated.vimonitoringservice.IVStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamAllIVStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * monitoring service
   * </pre>
   */
  public static final class IVMonitoringServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<IVMonitoringServiceBlockingStub> {
    private IVMonitoringServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IVMonitoringServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IVMonitoringServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. fetch and monitor patient's drip condition (normal too fast or too slow)
     * Unary
     * </pre>
     */
    public grpc.generated.vimonitoringservice.IVStatusResponse getIVStatus(grpc.generated.vimonitoringservice.IVStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetIVStatusMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 2. monitor the infusion status
     * server streaming
     * </pre>
     */
    public java.util.Iterator<grpc.generated.vimonitoringservice.IVStatusResponse> streamAllIVStatus(
        grpc.generated.vimonitoringservice.RequestAllStatus request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamAllIVStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * monitoring service
   * </pre>
   */
  public static final class IVMonitoringServiceFutureStub extends io.grpc.stub.AbstractFutureStub<IVMonitoringServiceFutureStub> {
    private IVMonitoringServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IVMonitoringServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IVMonitoringServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. fetch and monitor patient's drip condition (normal too fast or too slow)
     * Unary
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.generated.vimonitoringservice.IVStatusResponse> getIVStatus(
        grpc.generated.vimonitoringservice.IVStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetIVStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_IVSTATUS = 0;
  private static final int METHODID_STREAM_ALL_IVSTATUS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final IVMonitoringServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(IVMonitoringServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_IVSTATUS:
          serviceImpl.getIVStatus((grpc.generated.vimonitoringservice.IVStatusRequest) request,
              (io.grpc.stub.StreamObserver<grpc.generated.vimonitoringservice.IVStatusResponse>) responseObserver);
          break;
        case METHODID_STREAM_ALL_IVSTATUS:
          serviceImpl.streamAllIVStatus((grpc.generated.vimonitoringservice.RequestAllStatus) request,
              (io.grpc.stub.StreamObserver<grpc.generated.vimonitoringservice.IVStatusResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class IVMonitoringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IVMonitoringServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.vimonitoringservice.IVMonitoringServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("IVMonitoringService");
    }
  }

  private static final class IVMonitoringServiceFileDescriptorSupplier
      extends IVMonitoringServiceBaseDescriptorSupplier {
    IVMonitoringServiceFileDescriptorSupplier() {}
  }

  private static final class IVMonitoringServiceMethodDescriptorSupplier
      extends IVMonitoringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    IVMonitoringServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (IVMonitoringServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IVMonitoringServiceFileDescriptorSupplier())
              .addMethod(getGetIVStatusMethod())
              .addMethod(getStreamAllIVStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
