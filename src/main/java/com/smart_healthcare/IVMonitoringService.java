/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare;

import com.smart_healthcare.jmDNS.ServiceRegistration;
import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import grpc.generated.vimonitoringservice.IVMonitoringServiceImpl;
import grpc.generated.vimonitoringservice.*;
import grpc.generated.vimonitoringservice.IVMonitoringServiceGrpc.IVMonitoringServiceImplBase;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import javax.jmdns.JmDNS;

/**
 *
 * @author luyi
 */
public class IVMonitoringService extends IVMonitoringServiceImplBase {

    private static final Logger logger = Logger.getLogger(IVMonitoringService.class.getName());
    private static Server server;
    private static JmDNS jmdns;

    public static void main(String[] args) {

        IVMonitoringService ivmonitorrserver = new IVMonitoringService();

        int port = 50051;

        try {
            server = ServerBuilder.forPort(port)
                    .addService(ivmonitorrserver)
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            System.out.println("***** Server started, listening on" + port);

            // 2. register jsdns
            ServiceRegistration.register("_grpc._tcp.local.", "IVMonitoringService", port, "gRPC IVMonitoring service");
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
     * 1. fetch and monitor patient's drip condition (normal too fast or too slow)
     * Unary
     * </pre>
     */
    public void getIVStatus(IVStatusRequest request, StreamObserver<IVStatusResponse> responseObserver) {
        System.out.println("======================getIVStatus starting======================");
//        IVStatusResponse response = IVStatusResponse.newBuilder()
//                .setPatientId("200")
//                .setPatientName("amy")
//                .setRemaining(25.3)
//                .setStatus(1)
//                .build();
        IVStatusResponse.Builder response = IVStatusResponse.newBuilder();
        String id = request.getPatientId();
        // mock different cases
        if (id.equals("1")) {
            response
                    .setPatientId("1")
                    .setPatientName("Alice")
                    .setRemaining(50.5)
                    .setStatus(0);
        } else if (id.equals("2")) {
            response
                    .setPatientId("200")
                    .setPatientName("Bob")
                    .setRemaining(30.2)
                    .setStatus(1);
        } else if (id.equals("3")) {
            response
                    .setPatientId(id)
                    .setPatientName("Amy")
                    .setRemaining(3)
                    .setStatus(2);
        } else {
            response
                    .setPatientId(id)
                    .setPatientName("not exist")
                    .setRemaining(0.0)
                    .setStatus(3);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    /**
     * <pre>
     * 2. monitor the infusion status
     * server streaming
     * </pre>
     */
    public void streamAllIVStatus(RequestAllStatus request, StreamObserver<IVStatusResponse> responseObserver) {
        System.out.println("======================streamAllIVStatus starting======================");
        // this method should get all patients' VI status return back and display

        // simulate return a group of data
        String[] names = {"Liam", "Ava", "Oliver", "Peter", "James"};
        for (int i = 0; i < 5; i++) {
            double num = 1 + new Random().nextDouble() * (100 - 1);
            double newNum = Math.round(num * 10.0) / 10.0;
            // random number 0-2
            int statusNum = new Random().nextInt(3);
            IVStatusResponse response = IVStatusResponse.newBuilder()
                    .setPatientId("10" + i)
                    .setPatientName(names[i])
                    .setRemaining(newNum)
                    .setStatus(statusNum)
                    .build();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(IVMonitoringService.class.getName()).log(Level.SEVERE, null, ex);
            }
            // return response each time
            responseObserver.onNext(response);
        }
        // finish streaming
        responseObserver.onCompleted();

    }

    public static void disconnect() {
        if (jmdns != null) {
            jmdns.unregisterAllServices();
            try {
                jmdns.close();
            } catch (IOException ex) {
                Logger.getLogger(IVMonitoringService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("✅ jmDNS closed");
        }
        if (server != null) {
            server.shutdownNow();
            System.out.println("****************IVMonitoringServiceGrpc server shutdown***************");
        }
    }

}
