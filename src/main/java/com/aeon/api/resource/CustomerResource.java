package com.aeon.api.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

/**
 * Created by roshane on 11/9/2017.
 */
@Path("hello")
public class CustomerResource {

    private static final Logger logger = LogManager.getLogger("APP");
    private static final String SERVER_PORT = System.getProperty("server.port");

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> apiInfo() throws Exception {
//        throw new Exception("internal server error");
        logger.debug("hit for server [{}]", SERVER_PORT);
        return Collections.singletonMap("message", "api version V1.0.0");
    }

    @GET
    @Path("/1")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, LocalDateTime> getTime() {
        logger.debug("hit for server [{}]", SERVER_PORT);
        return Collections.singletonMap("dateTime", LocalDateTime.now());
    }
}
