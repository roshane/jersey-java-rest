package com.aeon.api;

import com.aeon.api.resource.ApplicationConfig;
import com.aeon.api.resource.CustomerResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

/**
 * Created by roshane on 11/9/2017.
 */
public class ApiBoot {

    private static final Logger logger = LogManager.getLogger(ApiBoot.class);

    public static void main(String[] args) {

        String serverPort = Optional.ofNullable(System.getProperty("server.port"))
                .orElse("8080");

        System.setProperty("server.port", serverPort);

        URI BASE_URI = URI.create(String.format("http://localhost:%s/api/", serverPort));

        addShutdownHook();

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

    static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.debug(">>> shutting down the service");
        }));
    }

    static ResourceConfig createApp() {
        ApplicationConfig config = new ApplicationConfig();
        config.registerClasses(Collections.singleton(CustomerResource.class));
        return config;
    }
}
