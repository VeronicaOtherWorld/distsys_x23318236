/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare;

import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import grpc.generated.dailyhealthmonitoringservice.DailyHealthMonitoringServiceImpl;
import grpc.generated.dailyhealthmonitoringservice.*;
import grpc.generated.dailyhealthmonitoringservice.DailyHealthMonitoringServiceGrpc.DailyHealthMonitoringServiceImplBase;
import static grpc.generated.dailyhealthmonitoringservice.DailyHealthMonitoringServiceGrpc.getReportAbnormalPatientsMethod;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 *
 * @author luyi
 */
public class HealthcareDaliyService extends DailyHealthMonitoringServiceImplBase {

    private static final Logger logger = Logger.getLogger(HealthcareDaliyService.class.getName());

    public static void main(String[] args) {

        HealthcareDaliyService greeterserver = new HealthcareDaliyService();

        int port = 50051;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(greeterserver)
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            System.out.println("***** Server started, listening on" + port);
            server.awaitTermination();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    /**
     * <pre>
     * 1. fetch and monitor patient's vital signs regularly
     * unary 
     * </pre>
     */
    public void collectPatientData(CollectRequest request, StreamObserver<CollectResponse> responseObserver) {

        System.out.println("========receiving sending collect patient data request=======");
        String res = "received";
        CollectResponse response = CollectResponse.newBuilder().setResult(res).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
       /**
     * <pre>
     * 2. nurse upload abnormal patients  
     * client streaming
     * </pre>
     */
    public StreamObserver<PatientAlertRequest> reportAbnormalPatients(
        StreamObserver<ReportStatusResponse> responseObserver) {
        System.out.println("reportAbnormalPatients method");
      return new StreamObserver<PatientAlertRequest>() {
            @Override
            public void onNext(PatientAlertRequest v) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void onError(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void onCompleted() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }

}
