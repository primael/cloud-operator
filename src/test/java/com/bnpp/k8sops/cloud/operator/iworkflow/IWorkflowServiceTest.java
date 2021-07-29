package com.bnpp.k8sops.cloud.operator.iworkflow;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import com.bnpp.k8sops.cloud.operator.iworkflow.mock.IWorkflowMock;
import com.bnpp.k8sops.cloud.operator.iworkflow.service.IWorkflowService;
import com.google.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(IWorkflowMock.class)
public class IWorkflowServiceTest {

    @Inject
    @RestClient
    IWorkflowService service;

    @Test
    public void testIWorkflowNameEndPoint() {
        service.read("123456789-45246");
    }
}
