package com.aeon.api;

import com.aeon.api.resource.ApplicationConfig;
import org.apache.logging.log4j.LogManager;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Created by roshane on 11/9/2017.
 */
public class ApiBoot {

    private static final URI BASE_URI = URI.create("http://localhost:8080/api/");

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ApiBoot.class);

    public static void main(String[] args) {
        try {
            logger.debug("application starting");
            System.out.println("JSON with Jackson Jersey Example App");

            ResourceConfig configuration = createApp();
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, configuration, false);
            server
                    .getServerConfiguration().getHttpHandlersWithMapping()
                    .entrySet().forEach(e -> {
                System.out.println(e.getKey());
                System.out.println(e.getValue()[0].getUrlPattern());
            });
            System.out.println(configuration.getResources());
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
            server.start();

            System.out.println(String.format("Application started.%nStop the application using CTRL+C"));

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            logger.error("error {}", ex);
        }

    }

    static ResourceConfig createApp() {
        return new ApplicationConfig();
    }
}
