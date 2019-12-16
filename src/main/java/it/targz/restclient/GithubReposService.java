package it.targz.restclient;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * GithubService
 */
@Path("repos")
@RegisterRestClient
public interface GithubReposService {

    @GET
    @Path("{owner}/{project}/releases/latest")
    @Produces(MediaType.APPLICATION_JSON)
    Release getLatestRelease(@PathParam("owner") String owner, @PathParam("project") String project);

    @GET
    @Path("{owner}/{project}/releases")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Release> getReleases(@PathParam("owner") String owner, @PathParam("project") String project);

    @GET
    @Path("{owner}/{project}/tags")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Release> getTags(@PathParam("owner") String owner, @PathParam("project") String project);
}