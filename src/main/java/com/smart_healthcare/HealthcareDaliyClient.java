/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.time.LocalTime;

// messages
import grpc.generated.dailyhealthmonitoringservice.CollectRequest;
import grpc.generated.dailyhealthmonitoringservice.CollectResponse;
import grpc.generated.dailyhealthmonitoringservice.PatientAlertRequest;
import grpc.generated.dailyhealthmonitoringservice.ReportStatusResponse;

import grpc.generated.dailyhealthmonitoringservice.DailyHealthMonitoringServiceGrpc;
import grpc.generated.dailyhealthmonitoringservice.DailyHealthMonitoringServiceGrpc.*;

/**
 *
 * @author luyi
 */
public class HealthcareDaliyClient {

    // a non-blocking stub to make an asynchronous call
    private static DailyHealthMonitoringServiceStub asyncStub;
    // add blockingStub 
    // for Unary RPC
    private static DailyHealthMonitoringServiceGrpc.DailyHealthMonitoringServiceBlockingStub blockingStub;

    public static void main(String[] args) {

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
//        requestMonitorTheTemperature();
    }

    // request patient information
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
}
