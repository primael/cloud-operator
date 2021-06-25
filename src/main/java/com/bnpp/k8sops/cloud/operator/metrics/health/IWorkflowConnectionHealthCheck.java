package com.bnpp.k8sops.cloud.operator.metrics.health;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
@Readiness
public class IWorkflowConnectionHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("iWorkflow connection health check");

        try {
            this.iWorkflowStatus();
            responseBuilder.up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }

    private void iWorkflowStatus() {
        Random rd = new Random();
        boolean status = rd.nextBoolean();

        if (!status) {
            throw new IllegalStateException("iWorkflow is not responding");
        }
    }
}
