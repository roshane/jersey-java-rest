package com.aeon.api.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roshane on 3/18/18.
 */
@Provider
public class InternalServerErrorMapper implements ExceptionMapper<InternalServerErrorException> {

    private static final Logger logger = LogManager.getLogger("org");

    @Override
    public Response toResponse(InternalServerErrorException exception) {

        logger.error("error occurred", exception);
        Map<String, Object> params = new HashMap<>();
        params.put("message", exception.getMessage());
        params.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(params)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
