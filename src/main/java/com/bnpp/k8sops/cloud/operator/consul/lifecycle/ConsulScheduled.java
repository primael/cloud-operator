package com.bnpp.k8sops.cloud.operator.consul.lifecycle;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.bnpp.k8sops.cloud.operator.consul.domain.Index;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.kv.Value;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class ConsulScheduled {

    @Inject
    Consul consulClient;

    @Inject
    MeterRegistry registry;

    /*
     * @Inject IWorkflowService service;
     */

    @Scheduled(every = "5s")
    void verify() {
        System.out.println("Interrogation consul");

        // 1- read index
        Optional<Value> indexes = consulClient.keyValueClient().getValue("config/cloud-operator/index");

        // 2- for each index in indexes call iWorkflow
        if (indexes.isPresent()) {
            String value = indexes.get().getValueAsString().get();
            Index index = this.getIndexes(value);
            index.getIndex().keySet().parallelStream().forEach(k -> this.readVipName(k));

            System.out.println(this.getIndexes(value));
        }
    }

    private Index getIndexes(String value) {
        Yaml yaml = new Yaml(new Constructor(Index.class));
        return yaml.load(value);
    }

    private void readVipName(String vip_name) {
        Optional<Value> vip = consulClient.keyValueClient().getValue("config/cloud-operator/" + vip_name);

        if (vip.isPresent()) {
            String value = vip.get().getValueAsString().get();

            // service.read();
            System.out.println(value);
        }
    }
}
