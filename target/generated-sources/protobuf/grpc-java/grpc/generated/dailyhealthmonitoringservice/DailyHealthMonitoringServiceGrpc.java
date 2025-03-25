package grpc.generated.dailyhealthmonitoringservice;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * monitoring service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: DailyHealthMonitoringService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DailyHealthMonitoringServiceGrpc {

  private DailyHealthMonitoringServiceGrpc() {}

  public static final String SERVICE_NAME = "smart_healthcare.DailyHealthMonitoringService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.dailyhealthmonitoringservice.CollectRequest,
      grpc.generated.dailyhealthmonitoringservice.CollectResponse> getCollectPatientDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CollectPatientData",
      requestType = grpc.generated.dailyhealthmonitoringservice.CollectRequest.class,
      responseType = grpc.generated.dailyhealthmonitoringservice.CollectResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.generated.dailyhealthmonitoringservice.CollectRequest,
      grpc.generated.dailyhealthmonitoringservice.CollectResponse> getCollectPatientDataMethod() {
    io.grpc.MethodDescriptor<grpc.generated.dailyhealthmonitoringservice.CollectRequest, grpc.generated.dailyhealthmonitoringservice.CollectResponse> getCollectPatientDataMethod;
    if ((getCollectPatientDataMethod = DailyHealthMonitoringServiceGrpc.getCollectPatientDataMethod) == null) {
      synchronized (DailyHealthMonitoringServiceGrpc.class) {
        if ((getCollectPatientDataMethod = DailyHealthMonitoringServiceGrpc.getCollectPatientDataMethod) == null) {
          DailyHealthMonitoringServiceGrpc.getCollectPatientDataMethod = getCollectPatientDataMethod =
              io.grpc.MethodDescriptor.<grpc.generated.dailyhealthmonitoringservice.CollectRequest, grpc.generated.dailyhealthmonitoringservice.CollectResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CollectPatientData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.dailyhealthmonitoringservice.CollectRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.dailyhealthmonitoringservice.CollectResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DailyHealthMonitoringServiceMethodDescriptorSupplier("CollectPatientData"))
              .build();
        }
      }
    }
    return getCollectPatientDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest,
      grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse> getReportAbnormalPatientsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportAbnormalPatients",
      requestType = grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest.class,
      responseType = grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest,
      grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse> getReportAbnormalPatientsMethod() {
    io.grpc.MethodDescriptor<grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest, grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse> getReportAbnormalPatientsMethod;
    if ((getReportAbnormalPatientsMethod = DailyHealthMonitoringServiceGrpc.getReportAbnormalPatientsMethod) == null) {
      synchronized (DailyHealthMonitoringServiceGrpc.class) {
        if ((getReportAbnormalPatientsMethod = DailyHealthMonitoringServiceGrpc.getReportAbnormalPatientsMethod) == null) {
          DailyHealthMonitoringServiceGrpc.getReportAbnormalPatientsMethod = getReportAbnormalPatientsMethod =
              io.grpc.MethodDescriptor.<grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest, grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportAbnormalPatients"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DailyHealthMonitoringServiceMethodDescriptorSupplier("ReportAbnormalPatients"))
              .build();
        }
      }
    }
    return getReportAbnormalPatientsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DailyHealthMonitoringServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DailyHealthMonitoringServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DailyHealthMonitoringServiceStub>() {
        @java.lang.Override
        public DailyHealthMonitoringServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DailyHealthMonitoringServiceStub(channel, callOptions);
        }
      };
    return DailyHealthMonitoringServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DailyHealthMonitoringServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DailyHealthMonitoringServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DailyHealthMonitoringServiceBlockingStub>() {
        @java.lang.Override
        public DailyHealthMonitoringServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DailyHealthMonitoringServiceBlockingStub(channel, callOptions);
        }
      };
    return DailyHealthMonitoringServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DailyHealthMonitoringServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DailyHealthMonitoringServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DailyHealthMonitoringServiceFutureStub>() {
        @java.lang.Override
        public DailyHealthMonitoringServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DailyHealthMonitoringServiceFutureStub(channel, callOptions);
        }
      };
    return DailyHealthMonitoringServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * monitoring service
   * </pre>
   */
  public static abstract class DailyHealthMonitoringServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 1. fetch and monitor patient's vital signs regularly
     * unary 
     * </pre>
     */
    public void collectPatientData(grpc.generated.dailyhealthmonitoringservice.CollectRequest request,
        io.grpc.stub.StreamObserver<grpc.generated.dailyhealthmonitoringservice.CollectResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCollectPatientDataMethod(), responseObserver);
    }

    /**
     * <pre>
     * 2. nurse upload abnormal patients  client streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest> reportAbnormalPatients(
        io.grpc.stub.StreamObserver<grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getReportAbnormalPatientsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCollectPatientDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                grpc.generated.dailyhealthmonitoringservice.CollectRequest,
                grpc.generated.dailyhealthmonitoringservice.CollectResponse>(
                  this, METHODID_COLLECT_PATIENT_DATA)))
          .addMethod(
            getReportAbnormalPatientsMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest,
                grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse>(
                  this, METHODID_REPORT_ABNORMAL_PATIENTS)))
          .build();
    }
  }

  /**
   * <pre>
   * monitoring service
   * </pre>
   */
  public static final class DailyHealthMonitoringServiceStub extends io.grpc.stub.AbstractAsyncStub<DailyHealthMonitoringServiceStub> {
    private DailyHealthMonitoringServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DailyHealthMonitoringServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DailyHealthMonitoringServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. fetch and monitor patient's vital signs regularly
     * unary 
     * </pre>
     */
    public void collectPatientData(grpc.generated.dailyhealthmonitoringservice.CollectRequest request,
        io.grpc.stub.StreamObserver<grpc.generated.dailyhealthmonitoringservice.CollectResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCollectPatientDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 2. nurse upload abnormal patients  client streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest> reportAbnormalPatients(
        io.grpc.stub.StreamObserver<grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getReportAbnormalPatientsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * monitoring service
   * </pre>
   */
  public static final class DailyHealthMonitoringServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DailyHealthMonitoringServiceBlockingStub> {
    private DailyHealthMonitoringServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DailyHealthMonitoringServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DailyHealthMonitoringServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. fetch and monitor patient's vital signs regularly
     * unary 
     * </pre>
     */
    public grpc.generated.dailyhealthmonitoringservice.CollectResponse collectPatientData(grpc.generated.dailyhealthmonitoringservice.CollectRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCollectPatientDataMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * monitoring service
   * </pre>
   */
  public static final class DailyHealthMonitoringServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DailyHealthMonitoringServiceFutureStub> {
    private DailyHealthMonitoringServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DailyHealthMonitoringServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DailyHealthMonitoringServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 1. fetch and monitor patient's vital signs regularly
     * unary 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.generated.dailyhealthmonitoringservice.CollectResponse> collectPatientData(
        grpc.generated.dailyhealthmonitoringservice.CollectRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCollectPatientDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COLLECT_PATIENT_DATA = 0;
  private static final int METHODID_REPORT_ABNORMAL_PATIENTS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DailyHealthMonitoringServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DailyHealthMonitoringServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COLLECT_PATIENT_DATA:
          serviceImpl.collectPatientData((grpc.generated.dailyhealthmonitoringservice.CollectRequest) request,
              (io.grpc.stub.StreamObserver<grpc.generated.dailyhealthmonitoringservice.CollectResponse>) responseObserver);
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
        case METHODID_REPORT_ABNORMAL_PATIENTS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.reportAbnormalPatients(
              (io.grpc.stub.StreamObserver<grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DailyHealthMonitoringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DailyHealthMonitoringServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.dailyhealthmonitoringservice.DailyHealthMonitoringServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DailyHealthMonitoringService");
    }
  }

  private static final class DailyHealthMonitoringServiceFileDescriptorSupplier
      extends DailyHealthMonitoringServiceBaseDescriptorSupplier {
    DailyHealthMonitoringServiceFileDescriptorSupplier() {}
  }

  private static final class DailyHealthMonitoringServiceMethodDescriptorSupplier
      extends DailyHealthMonitoringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DailyHealthMonitoringServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (DailyHealthMonitoringServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DailyHealthMonitoringServiceFileDescriptorSupplier())
              .addMethod(getCollectPatientDataMethod())
              .addMethod(getReportAbnormalPatientsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
