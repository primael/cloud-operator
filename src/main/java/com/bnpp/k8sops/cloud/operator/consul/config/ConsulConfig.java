package com.bnpp.k8sops.cloud.operator.consul.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.orbitz.consul.Consul;

import org.eclipse.microprofile.config.inject.ConfigProperty;

//@ApplicationScoped
public class ConsulConfig {

    @ConfigProperty(name = "consul.url")

    String consulUrl;
    @Produces
    Consul consulClient = Consul.builder() //
            .withUrl(consulUrl) //
            .withHttps(false) //
            .build();            
}
