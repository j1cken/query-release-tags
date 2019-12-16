package it.targz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import it.targz.restclient.GithubProject;

/**
 * AppLifecycleBean
 */
@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger LOGGER = LoggerFactory.getLogger("AppLifecycleBean");

    Map<String, GithubProject> my_map = new HashMap<String, GithubProject>();

    @ConfigProperty(name = "project.list.path", defaultValue = "/work/projects.csv")
    String list;

    void onStart(@Observes StartupEvent ev) throws IOException {
        LOGGER.info("The application is starting... loading list of projects...");

        BufferedReader reader = new BufferedReader(new FileReader(list));
        CSVParser parser = CSVFormat.DEFAULT.withHeader("shortname", "type", "istag", "owner", "project").withIgnoreSurroundingSpaces().parse(reader);

        parser.forEach(record -> {
            my_map.put(record.get("shortname"), new GithubProject(record.get("shortname"), record.get("type"), record.get("istag"), record.get("owner"), record.get("project")));
        });

    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping...");
    }

    Map<String, GithubProject> getMyMap() {
        return my_map;
    }

}