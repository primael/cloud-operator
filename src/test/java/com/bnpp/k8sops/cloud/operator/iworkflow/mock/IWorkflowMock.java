package com.bnpp.k8sops.cloud.operator.iworkflow.mock;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.util.Collections;
import java.util.Map;

public class IWorkflowMock implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();

        stubFor(get(urlEqualTo("/mgmt/deploy"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withBody("")));

        stubFor(get(urlMatching(".*")).atPriority(10).willReturn(aResponse().proxiedFrom("https://iworkflow/api")));

        return Collections.singletonMap("com.bnpp.k8sops.cloud.operator.iworkflow.service.IWorkflowService/mp-rest/url",
                wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }

    }

}
