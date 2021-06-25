package com.bnpp.k8sops.cloud.operator.consul.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Index {

    private Map<String, String> index;

    public Index() {
    }

    public Index(Map<String, String> index) {
        this.index = index;
    }

    public Map<String, String> getIndex() {
        return this.index;
    }

    public void setIndex(Map<String, String> index) {
        this.index = index;
    }

    public Index index(Map<String, String> index) {
        setIndex(index);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Index)) {
            return false;
        }
        Index index = (Index) o;
        return Objects.equals(index, index.index);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(index);
    }

    @Override
    public String toString() {
        return "{" + " index='" + getIndex() + "'" + "}";
    }

}
