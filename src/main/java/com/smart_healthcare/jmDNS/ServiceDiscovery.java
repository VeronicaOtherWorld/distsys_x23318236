package com.smart_healthcare.jmDNS;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author luyi
 */
import com.smart_healthcare.AIDiagnosticsClient;
import com.smart_healthcare.HealthcareDailyClient;
import com.smart_healthcare.IVMonitoringClient;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

// This code is adapted from https://github.com/jmdns/jmdns
public class ServiceDiscovery {

    private static class SampleListener implements ServiceListener {

        public void serviceAdded(ServiceEvent event) {
            System.out.println("✅✅✅ Service Added: " + event.getName());
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(500);
                        event.getDNS().requestServiceInfo(event.getType(), event.getName(), true);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            t.start();

        }

        public void serviceRemoved(ServiceEvent event) {
            System.out.println("Service removed: " + event.getInfo());
        }

        public void serviceResolved(ServiceEvent event) {
            System.out.println("🎯🎯🎯 Service resolved: " + event.getInfo());

            ServiceInfo info = event.getInfo();
            String serviceName = event.getName();

            String host = info.getHostAddresses()[0];
            int port = info.getPort();

            System.out.println("🎯🎯🎯 Service Resolved: host=" + host + " port=" + port);

            // call the method in the client side to create the channel
            switch (serviceName) {
                case "HealthcareDailyService":
                    HealthcareDailyClient.connectToServer(host, port);
//                    HealthcareDailyClient.requestPatientData();
                    break;
                case "IVMonitoringService":
                    IVMonitoringClient.connectToServer(host, port);
//                    IVMonitoringClient.requestVIStatus();
                    break;
                case "AIDiagnoseticService":
                    AIDiagnosticsClient.connectToServer(host, port);
//                    AIDiagnosticsClient.requestPatientInfo();
                    break;
                default:
                    System.out.println("Unknown service: " + serviceName);
            }
        }
    }

    public static void discoverGrpcService() {

        System.out.println("🟢 listening...");
        try {

            // Create a JmDNS instance
//            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
//                JmDNS jmdns = JmDNS.create();
            InetAddress ipv4Address = getFirstNonLoopbackIPv4Address();
            System.out.println("👀👀👀 : register" + ipv4Address.getHostAddress());
            JmDNS jmdns = JmDNS.create(ipv4Address);
            // Add a service listener
            jmdns.addServiceListener("_grpc._tcp.local.", new SampleListener());

            try {
                // Wait a bit
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServiceDiscovery.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // sometimes will return inet6address cause error
    // only return ipv4

    private static InetAddress getFirstNonLoopbackIPv4Address() throws SocketException {

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            if (iface.isLoopback() || !iface.isUp()) {
                continue;
            }

            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (addr instanceof Inet4Address) {
                    System.out.println("Found address: " + addr.getHostAddress());
                    // return addr
                    return addr;
                }
            }
        }
        return null;
    }
}
