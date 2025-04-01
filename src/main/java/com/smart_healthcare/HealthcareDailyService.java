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
import io.grpc.Status;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import java.util.ArrayList;

/**
 *
 * @author luyi
 */
public class HealthcareDailyService extends DailyHealthMonitoringServiceImplBase {

    private static final Logger logger = Logger.getLogger(HealthcareDailyService.class.getName());

    public static void main(String[] args) {

        HealthcareDailyService dailyserver = new HealthcareDailyService();

        int port = 50051;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(dailyserver)
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
     *
     * @param responseObserver - response to the client is return via this
     * object
     * @return
     */
    public StreamObserver<PatientAlertRequest> reportAbnormalPatients(
            StreamObserver<ReportStatusResponse> responseObserver) {
        // this method should gather multiple request from the client
        // server uses stream observer that gather the requests one by one in onNext()
        // after receiving all requests, i will give a response result in onCompleted()
        // this method returns a observer object
        // client side can create a instance of StreamObserver to observer the response
        System.out.println("reportAbnormalPatients method");
        return new StreamObserver<PatientAlertRequest>() {
            ArrayList<PatientAlertRequest> requestList = new ArrayList();

            @Override
            public void onNext(PatientAlertRequest v) {
                responseObserver.onError(
                        Status.INVALID_ARGUMENT
                                .withDescription("User ID is required")
                                .asRuntimeException()
                );
//                System.out.println("----receive message, the id is " + v.getPatientId()
//                        + ", the name is " + v.getPatientName() 
//                        + ", the abnormal message is " + v.getMessage());
//                requestList.add(v);

            }

            @Override
            public void onError(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            // after receving all the messages,
            // send back a message, tell client how many paitents information we received
            // use responseObserver.onNext()
            public void onCompleted() {
                String res = "-------we've receviced " + requestList.size() + " records!";
                System.out.println(res);
                ReportStatusResponse response = ReportStatusResponse.newBuilder().setMessage(res).build();

                //call this method only once
                responseObserver.onNext(response);
                // finished
                responseObserver.onCompleted();

            }
        };
    }

}
