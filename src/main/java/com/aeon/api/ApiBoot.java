package com.aeon.api;

import com.aeon.api.config.ApiResourceConfig;
import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
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

        URI BASE_URI = UriBuilder.fromUri("http://localhost")
                .path("/api")
                .port(Integer.valueOf(serverPort))
                .build();

        try {
            logger.debug("application starting");
            System.out.println("JSON with Jackson Jersey Example App");

            ResourceConfig configuration = createApp();
            final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, configuration, null);
            addShutdownHook(server);
            System.out.println(String.format("Application started. (HTTP/2 enabled!)\nTry out %s\nStop the application using "
                    + "CTRL+C.", BASE_URI));
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            logger.error("error {}", ex);
        }

    }

    static void addShutdownHook(final Channel channel) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            channel.close();
            logger.debug(">>> shutting down the service");
        }));
    }

    static ResourceConfig createApp() {
        ApiResourceConfig config = new ApiResourceConfig();
//        config.register(new CustomerResource());
        return config;
    }
}
