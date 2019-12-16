package it.targz;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import it.targz.restclient.GithubProject;
import it.targz.restclient.GithubReposService;
import it.targz.restclient.Release;

@Path("/")
public class Resource {

    @Inject
    @RestClient
    GithubReposService github;

    @Inject
    AppLifecycleBean bean;

    @GET
    @Path("{shortname}/1")
    @Produces(MediaType.APPLICATION_JSON)
    public Release getLatestRelease(@PathParam("shortname") String shortname) {
        GithubProject details = bean.getMyMap().get(shortname);
        if (details != null) {
            Release latestRelease = github.getLatestRelease(details.getOwner(), details.getProjectName());
            return latestRelease;
        } else {
            return new Release("not found");
        }
    }

    @GET
    @Path("{shortname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Release> getLatestVersions(@PathParam("shortname") String shortname, @PathParam("amount") int amount) {
        GithubProject details = bean.getMyMap().get(shortname);
        if (details != null) {
            if (details.isTag()) {
                return github.getTags(details.getOwner(),  details.getProjectName());
            } else {
                return github.getReleases(details.getOwner(), details.getProjectName());
            }
        } else {
            return new HashSet<>();
        }
    }
}

