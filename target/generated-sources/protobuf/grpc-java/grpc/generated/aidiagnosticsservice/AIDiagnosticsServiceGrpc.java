package grpc.generated.aidiagnosticsservice;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: AIDiagnosticsService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AIDiagnosticsServiceGrpc {

  private AIDiagnosticsServiceGrpc() {}

  public static final String SERVICE_NAME = "healthcare.AIDiagnosticsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.aidiagnosticsservice.PatientDataRequest,
      grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse> getStreamPatientDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamPatientData",
      requestType = grpc.generated.aidiagnosticsservice.PatientDataRequest.class,
      responseType = grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.aidiagnosticsservice.PatientDataRequest,
      grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse> getStreamPatientDataMethod() {
    io.grpc.MethodDescriptor<grpc.generated.aidiagnosticsservice.PatientDataRequest, grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse> getStreamPatientDataMethod;
    if ((getStreamPatientDataMethod = AIDiagnosticsServiceGrpc.getStreamPatientDataMethod) == null) {
      synchronized (AIDiagnosticsServiceGrpc.class) {
        if ((getStreamPatientDataMethod = AIDiagnosticsServiceGrpc.getStreamPatientDataMethod) == null) {
          AIDiagnosticsServiceGrpc.getStreamPatientDataMethod = getStreamPatientDataMethod =
              io.grpc.MethodDescriptor.<grpc.generated.aidiagnosticsservice.PatientDataRequest, grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamPatientData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.aidiagnosticsservice.PatientDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AIDiagnosticsServiceMethodDescriptorSupplier("StreamPatientData"))
              .build();
        }
      }
    }
    return getStreamPatientDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.generated.aidiagnosticsservice.DoctorRequest,
      grpc.generated.aidiagnosticsservice.AIResponse> getStreamAIDiagnosisMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamAIDiagnosis",
      requestType = grpc.generated.aidiagnosticsservice.DoctorRequest.class,
      responseType = grpc.generated.aidiagnosticsservice.AIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.aidiagnosticsservice.DoctorRequest,
      grpc.generated.aidiagnosticsservice.AIResponse> getStreamAIDiagnosisMethod() {
    io.grpc.MethodDescriptor<grpc.generated.aidiagnosticsservice.DoctorRequest, grpc.generated.aidiagnosticsservice.AIResponse> getStreamAIDiagnosisMethod;
    if ((getStreamAIDiagnosisMethod = AIDiagnosticsServiceGrpc.getStreamAIDiagnosisMethod) == null) {
      synchronized (AIDiagnosticsServiceGrpc.class) {
        if ((getStreamAIDiagnosisMethod = AIDiagnosticsServiceGrpc.getStreamAIDiagnosisMethod) == null) {
          AIDiagnosticsServiceGrpc.getStreamAIDiagnosisMethod = getStreamAIDiagnosisMethod =
              io.grpc.MethodDescriptor.<grpc.generated.aidiagnosticsservice.DoctorRequest, grpc.generated.aidiagnosticsservice.AIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamAIDiagnosis"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.aidiagnosticsservice.DoctorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.aidiagnosticsservice.AIResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AIDiagnosticsServiceMethodDescriptorSupplier("StreamAIDiagnosis"))
              .build();
        }
      }
    }
    return getStreamAIDiagnosisMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AIDiagnosticsServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AIDiagnosticsServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AIDiagnosticsServiceStub>() {
        @java.lang.Override
        public AIDiagnosticsServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AIDiagnosticsServiceStub(channel, callOptions);
        }
      };
    return AIDiagnosticsServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AIDiagnosticsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AIDiagnosticsServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AIDiagnosticsServiceBlockingStub>() {
        @java.lang.Override
        public AIDiagnosticsServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AIDiagnosticsServiceBlockingStub(channel, callOptions);
        }
      };
    return AIDiagnosticsServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AIDiagnosticsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AIDiagnosticsServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AIDiagnosticsServiceFutureStub>() {
        @java.lang.Override
        public AIDiagnosticsServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AIDiagnosticsServiceFutureStub(channel, callOptions);
        }
      };
    return AIDiagnosticsServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AIDiagnosticsServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 1. doctor keeps sending patient information 
     * then ai gives a diagnose
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.PatientDataRequest> streamPatientData(
        io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getStreamPatientDataMethod(), responseObserver);
    }

    /**
     * <pre>
     * 2. a doctor and AI start communicating in real time
     * bi streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.DoctorRequest> streamAIDiagnosis(
        io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.AIResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getStreamAIDiagnosisMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStreamPatientDataMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                grpc.generated.aidiagnosticsservice.PatientDataRequest,
                grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse>(
                  this, METHODID_STREAM_PATIENT_DATA)))
          .addMethod(
            getStreamAIDiagnosisMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.generated.aidiagnosticsservice.DoctorRequest,
                grpc.generated.aidiagnosticsservice.AIResponse>(
                  this, METHODID_STREAM_AIDIAGNOSIS)))
          .build();
    }
  }

  /**
   */
  public static final class AIDiagnosticsServiceStub extends io.grpc.stub.AbstractAsyncStub<AIDiagnosticsServiceStub> {
    private AIDiagnosticsServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AIDiagnosticsServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AIDiagnosticsServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. doctor keeps sending patient information 
     * then ai gives a diagnose
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.PatientDataRequest> streamPatientData(
        io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getStreamPatientDataMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 2. a doctor and AI start communicating in real time
     * bi streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.DoctorRequest> streamAIDiagnosis(
        io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.AIResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getStreamAIDiagnosisMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class AIDiagnosticsServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AIDiagnosticsServiceBlockingStub> {
    private AIDiagnosticsServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AIDiagnosticsServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AIDiagnosticsServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class AIDiagnosticsServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AIDiagnosticsServiceFutureStub> {
    private AIDiagnosticsServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AIDiagnosticsServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AIDiagnosticsServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_STREAM_PATIENT_DATA = 0;
  private static final int METHODID_STREAM_AIDIAGNOSIS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AIDiagnosticsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AIDiagnosticsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STREAM_PATIENT_DATA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamPatientData(
              (io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse>) responseObserver);
        case METHODID_STREAM_AIDIAGNOSIS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamAIDiagnosis(
              (io.grpc.stub.StreamObserver<grpc.generated.aidiagnosticsservice.AIResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AIDiagnosticsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AIDiagnosticsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.aidiagnosticsservice.AIDiagnosticsServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AIDiagnosticsService");
    }
  }

  private static final class AIDiagnosticsServiceFileDescriptorSupplier
      extends AIDiagnosticsServiceBaseDescriptorSupplier {
    AIDiagnosticsServiceFileDescriptorSupplier() {}
  }

  private static final class AIDiagnosticsServiceMethodDescriptorSupplier
      extends AIDiagnosticsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AIDiagnosticsServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AIDiagnosticsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AIDiagnosticsServiceFileDescriptorSupplier())
              .addMethod(getStreamPatientDataMethod())
              .addMethod(getStreamAIDiagnosisMethod())
              .build();
        }
      }
    }
    return result;
  }
}
