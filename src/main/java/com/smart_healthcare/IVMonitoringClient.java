/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare;

import com.smart_healthcare.jmDNS.ServiceDiscovery;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.logging.Logger;
// messages
import grpc.generated.vimonitoringservice.IVStatusRequest;
import grpc.generated.vimonitoringservice.IVStatusResponse;
import grpc.generated.vimonitoringservice.RequestAllStatus;

import grpc.generated.vimonitoringservice.IVMonitoringServiceGrpc;

import io.grpc.Context;
import io.grpc.Context.CancellableContext;
import io.grpc.StatusRuntimeException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 *
 * @author luyi
 */
public class IVMonitoringClient {

    // create cancellablecontext
    private static CancellableContext withCancellation;

    private static final Logger logger = Logger.getLogger(IVMonitoringClient.class.getName());
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
    public static String requestVIStatus(String patientId) {
        System.out.println("------===== Requesting vi status...=====----");
        String msg = "";
        IVStatusRequest request = IVStatusRequest.newBuilder()
                .setPatientId(patientId)
                .build();
        try {
            IVStatusResponse response = blockingStub.getIVStatus(request);
            int status = response.getStatus();
            String res = convertStatus(status);
            msg = "\nPatient name: " + response.getPatientName()
                    + "\nRemaining is : " + response.getRemaining() + "ml"
                    + "\nStatus: " + res + "\n";
            System.out.println("-------request result is : " + response.getPatientId()
                    + " name: " + response.getPatientName()
                    + " remaning: " + response.getRemaining()
                    + " status: " + response.getStatus());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            msg = "Something wrong happened: " 
                    + e.getStatus().getDescription();
        }
        return msg;

    }

    // request all patients' VI status
    public static String requestAllVIStatus(String nursetId) {
        StringBuffer sb = new StringBuffer();
        withCancellation = Context.current().withCancellation();

        // create request
        RequestAllStatus requestAll = RequestAllStatus.newBuilder().
                setNurseId(nursetId).build();

        try {
            Iterator<IVStatusResponse> item = blockingStub.streamAllIVStatus(requestAll);
            while (item.hasNext()) {
                IVStatusResponse response = item.next();
                int status = response.getStatus();
                String strStatus = convertStatus(status);

                sb.append("\n----------------\nPatient name: ")
                        .append(response.getPatientName())
                        .append("\nRemaining is : ")
                        .append(response.getRemaining())
                        .append("ml\nStatus: ").append(strStatus);

                System.out.println("----receive message, the id is " + response.getPatientId()
                        + ", the name is " + response.getPatientName()
                        + ", the remaining is " + response.getRemaining()
                        + ", the status is " + response.getStatus());
            }
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return "RPC failed: " + e.getStatus().getCode() +
                    ", Description: " + e.getStatus().getDescription();
        }
        return sb.toString();
    }

    private static String convertStatus(int status) {
        // handle with the status
        // 0 plenty, 1, almost finish 2 alert
        String res = "";
        switch (status) {
            case 0:
                res = "plenty";
                break;
            case 1:
                res = "almost finish";
                break;
            case 2:
                res = "watch out! this patient needs help!";
                break;
            default:
                break;
        }
        return res;
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
        //the deadline time out time is 30 sec
        asyncStub = IVMonitoringServiceGrpc.newStub(channel)
                .withCallCredentials(token)
                .withDeadlineAfter(60, TimeUnit.SECONDS);

        //        requestAverageTemperature();
        blockingStub = IVMonitoringServiceGrpc.newBlockingStub(channel)
                .withCallCredentials(token)
                .withDeadlineAfter(60, TimeUnit.SECONDS);
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
