/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare;

import com.google.common.base.Verify;
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
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author luyi
 */
public class HealthcareDailyClient {

    // a non-blocking stub to make an asynchronous call
    private static DailyHealthMonitoringServiceStub asyncStub;
    // add blockingStub 
    // for Unary RPC
    private static DailyHealthMonitoringServiceGrpc.DailyHealthMonitoringServiceBlockingStub blockingStub;

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        //non-blocking stub is for asynchronous calls
        //client does not wait for server to complete before starting to read responses
        //must use non-blocking stub for client streaming and bidirectional streaming
        //can also use for Server Streaming asynchronously 
        asyncStub = DailyHealthMonitoringServiceGrpc.newStub(channel);

//        requestAverageTemperature();
        blockingStub = DailyHealthMonitoringServiceGrpc.newBlockingStub(channel);

        // call the method to request send collection data
        requestPatientData();

        // call the client streaming and get the return message
        requestAbormalResult();
    }

    // request patient information
    // unary
    // 1.send info to the server
    private static void requestPatientData() {
        System.out.println("------===== Requesting monitor patient data...=====----");
        CollectRequest collectionRequest = CollectRequest.newBuilder()
                .setPatientId("001")
                .setHeartRate(80.0)
                .setPulse(75.0)
                .setTemperature(37.2)
                .build();
        // call the method unary rpc
        CollectResponse temp = blockingStub.collectPatientData(collectionRequest);

        System.out.println("-------request result is : " + temp.getResult());
    }

    // 2.send a series of abnormal patients' information
    private static void requestAbormalResult() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        System.out.println("Client Streaming - requestAbormalResult ");

        // observer if we get the response
        StreamObserver<ReportStatusResponse> responseObserver = new StreamObserver<ReportStatusResponse>() {
            @Override
            public void onNext(ReportStatusResponse v) {
                // get the message, tell us how many patients' they receive
                System.out.println("------response from server------" + v.getMessage());
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
        StreamObserver<PatientAlertRequest> requestObserver = asyncStub.reportAbnormalPatients(responseObserver);
        try {
            ArrayList abnormalPatients = new ArrayList();
            //add 1 patient
            requestObserver.onNext(PatientAlertRequest.newBuilder()
                    .setPatientId("024")
                    .setPatientName("john")
                    .setMessage("high press")
                    .build());

            Thread.sleep(500);

            // number 2 patient
            requestObserver.onNext(PatientAlertRequest.newBuilder()
                    .setPatientId("088")
                    .setPatientName("babel")
                    .setMessage("cough")
                    .build());

            Thread.sleep(500);

            // number 3 patient
            requestObserver.onNext(PatientAlertRequest.newBuilder()
                    .setPatientId("108")
                    .setPatientName("kathy")
                    .setMessage("back ache")
                    .build());

            Thread.sleep(500);

            // after finishing, tell server all done
            requestObserver.onCompleted();

            // give enough time to send request
            Thread.sleep(10000);

        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
