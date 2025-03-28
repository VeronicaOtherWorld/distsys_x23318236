/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

// messages
import grpc.generated.aidiagnosticsservice.PatientDataRequest;
import grpc.generated.aidiagnosticsservice.AIDiagnosticsResponse;
import grpc.generated.aidiagnosticsservice.DoctorRequest;
import grpc.generated.aidiagnosticsservice.AIResponse;

import grpc.generated.aidiagnosticsservice.AIDiagnosticsServiceGrpc;
import grpc.generated.aidiagnosticsservice.AIDiagnosticsServiceGrpc.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author luyi
 */
public class AIDiagnosticsClient {

    // a non-blocking stub to make an asynchronous call
    private static AIDiagnosticsServiceStub asyncStub;
    // add blockingStub
    // for Unary RPC
    private static AIDiagnosticsServiceBlockingStub blockingStub;
    private static ManagedChannel channel;

    public static void main(String[] args) throws InterruptedException {

        channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        //non-blocking stub is for asynchronous calls
        //client does not wait for server to complete before starting to read responses
        //must use non-blocking stub for client streaming and bidirectional streaming
        //can also use for Server Streaming asynchronously
        asyncStub = AIDiagnosticsServiceGrpc.newStub(channel);

//        requestAverageTemperature();
        blockingStub = AIDiagnosticsServiceGrpc.newBlockingStub(channel);

        // method 1 client streaming sending infos to server
        requestPatientInfo();
        // method 2 bi-di
        requestAIResponse();
    }

    // 1. steaming sending patients' information to server
    private static void requestPatientInfo() {
        System.out.println("--------Client Streaming - send info to server ------");

        //obseration teh result
        StreamObserver<AIDiagnosticsResponse> responseObserver = new StreamObserver<AIDiagnosticsResponse>() {
            @Override
            public void onNext(AIDiagnosticsResponse v) {
                // get the response
                System.out.println("------response from server------\n "
                        + "patient id: " + v.getPatientId()
                        + "diagnosis: " + v.getDiagnosis()
                        + "recommendation: " + v.getRecommendation());
            }

            @Override
            public void onError(Throwable thrwbl) {
                thrwbl.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("------stream is completed------");
            }
        };

        // start sending the result
        StreamObserver<PatientDataRequest> requestObserver = asyncStub.streamPatientData(responseObserver);
        try {
            requestObserver.onNext(PatientDataRequest.newBuilder()
                    .setPatientId("800")
                    .setDataType("Medical history")
                    .setValue("There are heart disease patients in the family.")
                    .build());

            Thread.sleep(500);

            requestObserver.onNext(PatientDataRequest.newBuilder()
                    .setPatientId("800")
                    .setDataType("Physical examination")
                    .setValue("Abnormal heartbeat, a murmur in the heart.")
                    .build());

            Thread.sleep(500);

            requestObserver.onNext(PatientDataRequest.newBuilder()
                    .setPatientId("800")
                    .setDataType("Lab tests")
                    .setValue("The levels of serum cardiac enzymes were increased significantly.")
                    .build());

            Thread.sleep(500);

            // after finishing, tell server all done
            requestObserver.onCompleted();

            // give enough time to send request
            Thread.sleep(1000);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 2. bi-di streaming doctor commutiate with ai
    
    /**
    * through requestObserver.onNext() send message
    * through responseObserver.onNext() receive message
     */
    private static void requestAIResponse() throws InterruptedException {
        
        // get the response from ai
        StreamObserver<AIResponse> responseObserver = new StreamObserver<AIResponse>(){
            @Override
            public void onNext(AIResponse v) {
                System.out.println("----------- ai response is " + v.getAnswer());
            }

            @Override
            public void onError(Throwable thrwbl) {
                System.out.println("----------- thrwbl" + thrwbl);
            }

            @Override
            public void onCompleted() {
                System.out.println("----------- ai response is end");
            }
        };
        
        // get the doctor request and send to server
        StreamObserver<DoctorRequest> requestObserver = 
                asyncStub.streamAIDiagnosis(responseObserver);
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the question");
        while(true){
            String msg = sc.nextLine();
            if("exit".equalsIgnoreCase(msg.trim())){
                requestObserver.onCompleted();
                break;
            }
            DoctorRequest request = DoctorRequest.newBuilder()
                    .setDoctorId("17")
                    .setMessage(msg)
                    .build();
            requestObserver.onNext(request);
        }
        Thread.sleep(1000);
        channel.shutdown();
        
    }
}
