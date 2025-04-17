/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare;

import com.smart_healthcare.jmDNS.ServiceDiscovery;
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
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author luyi
 */
public class AIDiagnosticsClient {

    // a non-blocking stub to make an asynchronous call
    public static AIDiagnosticsServiceStub asyncStub;
    // add blockingStub
    // for Unary RPC
    private static AIDiagnosticsServiceBlockingStub blockingStub;
    private static ManagedChannel channel;

    public static void main(String[] args) throws InterruptedException {

        //1. find and connect to the discovery
        ServiceDiscovery.discoverGrpcService();
        // method 1 client streaming sending infos to server
//        requestPatientInfo();
        // method 2 bi-di
//        requestAIResponse();
    }

    // 1. steaming sending patients' information to server
    public static StreamObserver<PatientDataRequest> requestPatientInfo(
            StreamObserver<AIDiagnosticsResponse> responseObserver) {
        System.out.println("--------Client Streaming - send info to server ------");

        //obseration teh result
        responseObserver = new StreamObserver<AIDiagnosticsResponse>() {
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
        return asyncStub.streamPatientData(responseObserver);
    }

    // 2. bi-di streaming doctor commutiate with ai
    /**
     * through requestObserver.onNext() send message through
     * responseObserver.onNext() receive message
     */
    public static void requestAIResponse() throws InterruptedException {

        // get the response from ai
        StreamObserver<AIResponse> responseObserver = new StreamObserver<AIResponse>() {
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
        StreamObserver<DoctorRequest> requestObserver
                = asyncStub.streamAIDiagnosis(responseObserver);
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the question");
        while (true) {
            String msg = sc.nextLine();
            if ("exit".equalsIgnoreCase(msg.trim())) {
                requestObserver.onCompleted();
                break;
            }
            DoctorRequest request = DoctorRequest.newBuilder()
                    .setDoctorId("17")
                    .setMessage(msg)
                    .build();
            requestObserver.onNext(request);
        }
        Thread.sleep(200);
//        channel.shutdown();

    }

    public static void connectToServer(String host, int port) {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdownNow();
        }
        
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        String jwt = getJwt();
        BearerToken token = new BearerToken(jwt);

        //non-blocking stub is for asynchronous calls
        //client does not wait for server to complete before starting to read responses
        //must use non-blocking stub for client streaming and bidirectional streaming
        //can also use for Server Streaming asynchronously
        asyncStub = AIDiagnosticsServiceGrpc.newStub(channel)
                .withCallCredentials(token)
                .withDeadlineAfter(100, TimeUnit.SECONDS);

//        requestAverageTemperature();

        // in case the not responsed stub block the progress, add timeout
        // if does not response after 30 sec, automatically finish
        blockingStub = AIDiagnosticsServiceGrpc.newBlockingStub(channel)
                .withCallCredentials(token)
                .withDeadlineAfter(30, TimeUnit.SECONDS);
        System.out.println("--------connect to grpc--------- " + host + ":" + port);
    }

    public static void disconnect() {
        if (channel != null) {
            channel.shutdownNow();
            System.out.println("****************AIDiagnosticsServiceGrpc channel shutdown***************");
        }
    }

    private static String getJwt() {
        return Jwts.builder()
                .setSubject("AIDiagnosticsClient") // client's identifier
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_SIGNING_KEY)
                .compact();
    }
}
