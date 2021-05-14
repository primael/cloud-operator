package com.bnpp.k8sops.cloud.operator;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.bnpp.k8sops.cloud.operator.watchers.LoadBalancerWatcher;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watcher;
import io.quarkus.runtime.StartupEvent;

public class Operator {

    @Inject
    KubernetesClient defaultClient;

    public void onStartup(@Observes StartupEvent event) {
        System.out.println("Starting");
        Watcher<Service> loadBalancerWatcher = new LoadBalancerWatcher();
        defaultClient.services().inAnyNamespace().watch(loadBalancerWatcher);
    }

}
