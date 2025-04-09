/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare;

import com.smart_healthcare.jmDNS.ServiceDiscovery;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.time.LocalTime;

// messages
import grpc.generated.vimonitoringservice.IVStatusRequest;
import grpc.generated.vimonitoringservice.IVStatusResponse;
import grpc.generated.vimonitoringservice.RequestAllStatus;

import grpc.generated.vimonitoringservice.IVMonitoringServiceGrpc;

import grpc.generated.vimonitoringservice.IVMonitoringServiceGrpc.IVMonitoringServiceBlockingStub;
import java.util.Iterator;

/**
 *
 * @author luyi
 */
public class IVMonitoringClient {

    // a non-blocking stub to make an asynchronous call
    public static IVMonitoringServiceGrpc.IVMonitoringServiceStub asyncStub;
    // add blockingStub 
    // for Unary RPC
    public static IVMonitoringServiceGrpc.IVMonitoringServiceBlockingStub blockingStub;

    private static ManagedChannel channel;

    public static void main(String[] args) throws InterruptedException {
        //1. find and connect to the discovery
        ServiceDiscovery.discoverGrpcService();
        // call the method to request one patient VI situation
//        requestVIStatus();

        // call the method to request all stituation
//        requestAllVIStatus();
    }

    // request a patient vi information
    public static void requestVIStatus(String patientId) {
        System.out.println("------===== Requesting vi status...=====----");
        IVStatusRequest request = IVStatusRequest.newBuilder()
                .setPatientId(patientId)
                .build();
        IVStatusResponse response = blockingStub.getIVStatus(request);
        System.out.println("-------request result is : " + response.getPatientId()
                + " name: " + response.getPatientName()
                + " remaning: " + response.getRemaining()
                + " status: " + response.getStatus());
    }

    // request all patients' VI status
    public static void requestAllVIStatus(String nursetId) {

        RequestAllStatus requestAll = RequestAllStatus.newBuilder().setNurseId(nursetId).build();

        Iterator<IVStatusResponse> item = blockingStub.streamAllIVStatus(requestAll);

        while (item.hasNext()) {
            IVStatusResponse response = item.next();
            System.out.println("----receive message, the id is " + response.getPatientId()
                    + ", the name is " + response.getPatientName()
                    + ", the remaining is " + response.getRemaining()
                    + ", the status is " + response.getStatus());
        }
    }

    public static void connectToServer(String host, int port) {
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        //non-blocking stub is for asynchronous calls
        //client does not wait for server to complete before starting to read responses
        //must use non-blocking stub for client streaming and bidirectional streaming
        //can also use for Server Streaming asynchronously 
        asyncStub = IVMonitoringServiceGrpc.newStub(channel);

        //        requestAverageTemperature();
        blockingStub = IVMonitoringServiceGrpc.newBlockingStub(channel);
        System.out.println("--------connect to grpc--------- " + host + ":" + port);
    }

    public static void disconnect() {
        if (channel != null) {
            channel.shutdownNow();
            System.out.println("****************IVMonitoringServiceGrpc channel shutdown***************");
        }
    }
}
