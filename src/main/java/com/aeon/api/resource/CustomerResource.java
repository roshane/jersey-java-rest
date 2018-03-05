package com.aeon.api.resource;

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

    @GET
    @Path("/1")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, LocalDateTime> getTime() {
        return Collections.singletonMap("dateTime", LocalDateTime.now());
    }
}
