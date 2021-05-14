package com.bnpp.k8sops.cloud.operator.watchers;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;

public class LoadBalancerWatcher implements Watcher<Service> {

    @Inject
    Logger log;

    @Override
    public void eventReceived(Action action, Service resource) {

        boolean isLoadBalancer = resource.getSpec().getType().contentEquals("NodePort");

        if (isLoadBalancer) {
            if (action.equals(Action.ADDED)) {
                // log.info("A new Loadbalancer was added.");
                System.out.println("A new Loadbalancer was added.");
            } else if (action.equals(Action.DELETED)) {
                System.out.println("A Loadbalancer was deleted.");
            } else if (action.equals(Action.MODIFIED)) {
                System.out.println("A Loadbalancer was modified.");
            } else if (action.equals(Action.ERROR)) {
                System.out.println("A Loadbalancer was errored.");
            }
        }
    }

    @Override
    public void onClose(WatcherException cause) {
        if (cause != null) {
            System.out.println(cause.getMessage());
        }
    }

}
