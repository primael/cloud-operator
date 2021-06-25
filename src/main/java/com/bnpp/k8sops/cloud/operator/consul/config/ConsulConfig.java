package com.bnpp.k8sops.cloud.operator.consul.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.orbitz.consul.Consul;

@ApplicationScoped
public class ConsulConfig {

    @Produces
    Consul consulClient = Consul.builder().build();

}
