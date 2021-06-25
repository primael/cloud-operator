package com.bnpp.k8sops.cloud.operator.metrics.health;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.orbitz.consul.Consul;
import com.orbitz.consul.model.ConsulResponse;
import com.orbitz.consul.model.health.Node;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
@Readiness
public class ConsulConnectionHealthCheck implements HealthCheck {

    @Inject
    Consul consulClient;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Consul connection health check");

        try {
            this.consulStatus();
            responseBuilder.up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }

    private void consulStatus() {

        ConsulResponse<List<Node>> nodeResponse = consulClient.catalogClient().getNodes();

        for (Node node : nodeResponse.getResponse()) {
            ConsulResponse<List<com.orbitz.consul.model.health.HealthCheck>> nodeHealth = consulClient.healthClient()
                    .getNodeChecks(node.getNode());
            for (com.orbitz.consul.model.health.HealthCheck healthCheck : nodeHealth.getResponse()) {
                System.out.println(healthCheck.getStatus());
            }
        }
    }

}
