/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;

// messages
import grpc.generated.dailyhealthmonitoringservice.CollectRequest;
import grpc.generated.dailyhealthmonitoringservice.CollectResponse;
import grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest;
import grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse;

import grpc.generated.dailyhealthmonitoringservice.DailyHealthMonitoringServiceGrpc;
import grpc.generated.dailyhealthmonitoringservice.DailyHealthMonitoringServiceGrpc.*;
import java.io.IOException;
import java.net.UnknownHostException;

import com.smart_healthcare.jmDNS.ServiceDiscovery;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextArea;

/**
 *
 * @author luyi
 */
public class HealthcareDailyClient {

    private static ManagedChannel channel;
    // a non-blocking stub to make an asynchronous call
    public static DailyHealthMonitoringServiceStub asyncStub;
    // add blockingStub 
    // for Unary RPC
    public static DailyHealthMonitoringServiceGrpc.DailyHealthMonitoringServiceBlockingStub blockingStub;

    public static StreamObserver<PatientAlertRequest> requestObserver = null;

    public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
        //1. find and connect to the discovery
        ServiceDiscovery.discoverGrpcService();
        //2. builder channel
//        ManagedChannel channel = ManagedChannelBuilder
//                .forAddress("localhost", 50051)
//                .usePlaintext()
//                .build();

        //non-blocking stub is for asynchronous calls
        //client does not wait for server to complete before starting to read responses
        //must use non-blocking stub for client streaming and bidirectional streaming
        //can also use for Server Streaming asynchronously 
//        asyncStub = DailyHealthMonitoringServiceGrpc.newStub(channel);
//        requestAverageTemperature();
//        blockingStub = DailyHealthMonitoringServiceGrpc.newBlockingStub(channel);
        // call the method to request send collection data
//        requestPatientData();
        // call the client streaming and get the return message
//        requestAbormalResult();
    }

    // request patient information
    // unary
    // 1.send info to the server
    public static String requestPatientData(String id, double heartRate, double pulse, double temperature) {
        System.out.println("------===== Requesting monitor patient data...=====----");
        CollectRequest collectionRequest = CollectRequest.newBuilder()
                .setPatientId(id)
                .setHeartRate(heartRate)
                .setPulse(pulse)
                .setTemperature(temperature)
                .build();
        // call the method unary rpc
        CollectResponse temp = blockingStub.collectPatientData(collectionRequest);

        System.out.println("-------request result is : " + temp.getResult());
        return "-------request result is : " + temp.getResult();
    }

    // 2.send a series of abnormal patients' information
    public static StreamObserver<PatientAlertRequest> requestAbormalResult(String id, String name, String desc, JTextArea resArea) throws InterruptedException {
//        final CountDownLatch latch = new CountDownLatch(1);
        System.out.println("Client Streaming - requestAbormalResult ");
        // observer if we get the response
        StreamObserver<ReportStatusResponse> responseObserver = new StreamObserver<ReportStatusResponse>() {
            @Override
            public void onNext(ReportStatusResponse v) {
                // get the message, tell us how many patients' they receive
                System.out.println("------response from server------" + v.getMessage());
                String msg = "------response from server------" + v.getMessage();
                resArea.append(msg);
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                switch (status.getCode()) {
                    case INVALID_ARGUMENT:
                        System.out.println("wrong parameter: " + status.getDescription());
                        break;
                    case UNAUTHENTICATED:
                        System.out.println("please login");
                        break;
                    case INTERNAL:
                        System.out.println("inner error");
                        break;
                    case UNAVAILABLE:
                        System.out.println("cannot connect to server");
                        break;
                    default:
                        System.out.println("ohter:" + status);
                }
            }

            @Override
            public void onCompleted() {
                System.out.println("------stream is completed------");
            }

        };

        // client uses asynchronous stub 
        // client can send request in onnext to ask response from server
        requestObserver = asyncStub.reportAbnormalPatients(responseObserver);
        try {
            //add 1 patient
            requestObserver.onNext(PatientAlertRequest.newBuilder()
                    .setPatientId(id)
                    .setPatientName(name)
                    .setMessage(desc)
                    .build());

            // after finishing, tell server all done
            // requestObserver.onCompleted();
            // give enough time to send request
            Thread.sleep(2000);

        } catch (RuntimeException e) {
            e.printStackTrace();
            responseObserver.onCompleted();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // ensure this is closed in the end
            requestObserver.onCompleted();
        }

        return requestObserver;
    }

    public static void connectToServer(String host, int port) {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdownNow();
        }

        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        // create token
        String jwt = getJwt();
        BearerToken token = new BearerToken(jwt);

        asyncStub = DailyHealthMonitoringServiceGrpc.newStub(channel)
                .withCallCredentials(token)
                .withDeadlineAfter(60, TimeUnit.SECONDS);
        blockingStub = DailyHealthMonitoringServiceGrpc.newBlockingStub(channel)
                .withCallCredentials(token)
                .withDeadlineAfter(60, TimeUnit.SECONDS); // connect
        System.out.println("--------connect to grpc--------- " + host + ":" + port);
    }

    public static void disconnect() {
        if (channel != null) {
            channel.shutdownNow();
            System.out.println("****************IVMonitoringServiceGrpc channel shutdown***************");
        }
    }

    private static String getJwt() {
        return Jwts.builder()
                .setSubject("AIDiagnosticsClient") // client's identifier
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_SIGNING_KEY)
                .compact();
    }
}
