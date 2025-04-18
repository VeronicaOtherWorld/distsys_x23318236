// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DailyHealthMonitoringService.proto

package grpc.generated.dailyhealthmonitoringservice;

public final class DailyHealthMonitoringServiceImpl {
  private DailyHealthMonitoringServiceImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_smart_healthcare_CollectRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_smart_healthcare_CollectRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_smart_healthcare_CollectResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_smart_healthcare_CollectResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_smart_healthcare_PatientAlertRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_smart_healthcare_PatientAlertRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_smart_healthcare_ReportStatusResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_smart_healthcare_ReportStatusResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"DailyHealthMonitoringService.proto\022\020sm" +
      "art_healthcare\"Z\n\016CollectRequest\022\021\n\tpati" +
      "entId\030\001 \001(\t\022\021\n\theartRate\030\002 \001(\001\022\r\n\005pulse\030" +
      "\003 \001(\001\022\023\n\013temperature\030\004 \001(\001\"!\n\017CollectRes" +
      "ponse\022\016\n\006result\030\001 \001(\t\"N\n\023PatientAlertReq" +
      "uest\022\021\n\tpatientId\030\001 \001(\t\022\023\n\013patientName\030\002" +
      " \001(\t\022\017\n\007message\030\003 \001(\t\"\'\n\024ReportStatusRes" +
      "ponse\022\017\n\007message\030\001 \001(\t2\344\001\n\034DailyHealthMo" +
      "nitoringService\022Y\n\022CollectPatientData\022 ." +
      "smart_healthcare.CollectRequest\032!.smart_" +
      "healthcare.CollectResponse\022i\n\026ReportAbno" +
      "rmalPatients\022%.smart_healthcare.PatientA" +
      "lertRequest\032&.smart_healthcare.ReportSta" +
      "tusResponse(\001BQ\n+grpc.generated.dailyhea" +
      "lthmonitoringserviceB DailyHealthMonitor" +
      "ingServiceImplP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_smart_healthcare_CollectRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_smart_healthcare_CollectRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_smart_healthcare_CollectRequest_descriptor,
        new java.lang.String[] { "PatientId", "HeartRate", "Pulse", "Temperature", });
    internal_static_smart_healthcare_CollectResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_smart_healthcare_CollectResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_smart_healthcare_CollectResponse_descriptor,
        new java.lang.String[] { "Result", });
    internal_static_smart_healthcare_PatientAlertRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_smart_healthcare_PatientAlertRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_smart_healthcare_PatientAlertRequest_descriptor,
        new java.lang.String[] { "PatientId", "PatientName", "Message", });
    internal_static_smart_healthcare_ReportStatusResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_smart_healthcare_ReportStatusResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_smart_healthcare_ReportStatusResponse_descriptor,
        new java.lang.String[] { "Message", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
