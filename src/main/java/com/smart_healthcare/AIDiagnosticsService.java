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

import grpc.generated.aidiagnosticsservice.AIDiagnosticsServiceImpl;
import grpc.generated.aidiagnosticsservice.*;
import grpc.generated.aidiagnosticsservice.AIDiagnosticsServiceGrpc.AIDiagnosticsServiceImplBase;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import java.util.ArrayList;

/**
 *
 * @author luyi
 */
public class AIDiagnosticsService extends AIDiagnosticsServiceImplBase {

    private static final Logger logger = Logger.getLogger(HealthcareDailyService.class.getName());

    public static void main(String[] args) {

        AIDiagnosticsService aiserver = new AIDiagnosticsService();

        int port = 50051;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(aiserver)
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

    /**
     * <pre>
     * 1. doctor keeps sending patient information
     * then ai gives a diagnose
     * client streaming
     * </pre>
     */
    public StreamObserver<PatientDataRequest> streamPatientData(
            StreamObserver<AIDiagnosticsResponse> responseObserver) {
        System.out.println("----------------client streaming streamPatientData method----------------");
        // create a list to contain the message sent from the client side
        ArrayList<PatientDataRequest> requestList = new ArrayList();

        // get the sending result
        return new StreamObserver<PatientDataRequest>() {
            @Override
            public void onNext(PatientDataRequest v) {
                // received the materials that client send one by one
                System.out.println("----receive message, the id is " + v.getPatientId()
                        + ", the data type is " + v.getDataType()
                        + ", the value is " + v.getValue());
                requestList.add(v);
            }

            @Override
            public void onError(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from 
            }

            @Override
            public void onCompleted() {
                String res = "-------we've receviced " + requestList.size() + " records!";
                System.out.println(res);
                // set the response to client
                // tell them what is the giagnosis result and give recommend therapies
                AIDiagnosticsResponse response = AIDiagnosticsResponse.newBuilder()
                        .setPatientId("800")
                        .setDiagnosis("heart disease")
                        .setRecommendation("Depends on those materials that received, the condition is very serious and may require emergency surgery.")
                        .build();

                // call next method only once
                responseObserver.onNext(response);
                // completed
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * <pre>
     * 2. a doctor and AI start communicating in real time
     * bi streaming
     * ai response to doctor
     * </pre>
     */
    
    /**
    * through responseObserver.onNext() response message
    * through onNext(request) receive request
     */
    public StreamObserver<DoctorRequest> streamAIDiagnosis(
            StreamObserver<AIResponse> responseObserver) {

        System.out.println("---------------- bi-di streaming streamAIDiagnosis method----------------");

        return new StreamObserver<DoctorRequest>() {
            @Override
            public void onNext(DoctorRequest v) {
                String doctorMsg = v.getMessage();
                // simuate the response
                String reply = "======= AI's advice: pay attention to " + doctorMsg + "=======";
                System.out.println("doctor request send message "
                        + " id: " + v.getDoctorId()
                        + " message: " + v.getMessage());
                AIResponse response = AIResponse.newBuilder()
                        .setDoctorId("17")
                        .setAnswer(reply)
                        .build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable thrwbl) {
                System.out.println("error" + thrwbl);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
