package com.bnpp.k8sops.cloud.operator.iworkflow.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.bnpp.k8sops.cloud.operator.iworkflow.get.response.GetResponse;
import com.bnpp.k8sops.cloud.operator.iworkflow.post.request.PostRequest;
import com.bnpp.k8sops.cloud.operator.iworkflow.post.response.PostResponse;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/mgmt")
@RegisterRestClient
public interface IWorkflowService {

    @POST
    @Path("/deploy")
    public PostResponse create(PostRequest request);

    @GET
    @Path("/deploy")
    public GetResponse read(String transactionId);

}
