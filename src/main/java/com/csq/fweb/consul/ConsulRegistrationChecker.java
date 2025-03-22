package com.csq.fweb.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class ConsulRegistrationChecker implements CommandLineRunner {

    @Autowired
    private ConsulDiscoveryClient consulDiscoveryClient;

    @Override
    public void run(String... args) throws Exception {
        try {
            // 尝试获取当前服务的实例信息
            consulDiscoveryClient.getInstances("your-service-name");
        } catch (Exception e) {
            throw new RuntimeException("Failed to register service to Consul", e);
        }
    }
}