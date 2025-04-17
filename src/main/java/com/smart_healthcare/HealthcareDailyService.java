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

import grpc.generated.dailyhealthmonitoringservice.*;
import grpc.generated.dailyhealthmonitoringservice.DailyHealthMonitoringServiceGrpc.DailyHealthMonitoringServiceImplBase;
import io.grpc.Status;
import java.util.ArrayList;
import com.smart_healthcare.jmDNS.ServiceRegistration;
import java.util.logging.Level;
import javax.jmdns.JmDNS;

/**
 *
 * @author luyi
 */
public class HealthcareDailyService extends DailyHealthMonitoringServiceImplBase {

    private static final Logger logger = Logger.getLogger(HealthcareDailyService.class.getName());
    private static Server server;
    private static JmDNS jmdns;

    public static void main(String[] args) {

        // 1,start the grpc, in 50051 port
        HealthcareDailyService dailyserver = new HealthcareDailyService();

        int port = 50051;

        try {
            server = ServerBuilder.forPort(port)
                    .addService(dailyserver)
                    .intercept(new AuthorizationServerInterceptor())
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            System.out.println("***** Server started, listening on" + port);

            // 2. register jsdns
            ServiceRegistration.register("_grpc._tcp.local.",
                    "HealthcareDailyService", port,
                    "gRPC daily healthcare service");

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
        String res = "received this information.";
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
                // mock validate if the id is empty
                if (v.getPatientId() == null || v.getPatientId().isEmpty()) {
                    responseObserver.onError(
                            Status.INVALID_ARGUMENT
                                    .withDescription("Patient ID is required!")
                                    .asRuntimeException()
                    );
                    // finish the progress
                    return;
                }
                // if the message is legal, add to arraylist
                System.out.println("----receive message, the id is " + v.getPatientId()
                        + ", the name is " + v.getPatientName()
                        + ", the abnormal message is " + v.getMessage());
                requestList.add(v);

            }

            @Override
            public void onError(Throwable thrwbl) {
                System.err.println("servet returns error: " + Status.fromThrowable(thrwbl));
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

    public static void disconnect() {
        if (jmdns != null) {
            jmdns.unregisterAllServices();
            try {
                jmdns.close();
            } catch (IOException ex) {
                Logger.getLogger(HealthcareDailyService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("----------------jmDNS closed------------------");
        }
        if (server != null) {
            server.shutdownNow();
            System.out.println("****************IVMonitoringServiceGrpc server shutdown***************");
        }
    }
}
