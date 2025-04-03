/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smart_healthcare.jmDNS;

/**
 *
 * @author luyi
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ServiceRegistration {

    public static void main(String[] args) throws InterruptedException {
        register("_grpc._tcp.local.", "DailyHealthcareService", 50051, "daily monitor health care");
        register("_grpc._tcp.local.", "IVMonitoringService", 50051, "IV monitor health care");
        register("_grpc._tcp.local.", "AIDiagnoseticService", 50051, "AI Diagnosetic");
        // Wait a bit
        Thread.sleep(20000);
    }

    public static void register(String type, String serviceName, int port, String description) {
        try {
            System.out.println("📌📌📌📌📌📌Registration: InetAddress.getLocalHost():" + InetAddress.getLocalHost());
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServiceRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Create a JmDNS instance
//            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            InetAddress ipv4Address = getFirstNonLoopbackIPv4Address();
            System.out.println("👀👀👀 使用 IPv4 地址注册服务: " + ipv4Address.getHostAddress());
            JmDNS jmdns = JmDNS.create(ipv4Address);

            // Register a service
            // Note that this code does not start the service. 
            ServiceInfo serviceInfo = ServiceInfo.create(type, serviceName, port, description);
            jmdns.registerService(serviceInfo);

            // Unregister all services
            //jmdns.unregisterAllServices();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static InetAddress getFirstNonLoopbackIPv4Address() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (!iface.isUp() || iface.isLoopback() || iface.isVirtual()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof java.net.Inet4Address && !addr.isLoopbackAddress()) {
                        return addr;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("❌ 没找到合适的 IPv4 地址");
    }
}
