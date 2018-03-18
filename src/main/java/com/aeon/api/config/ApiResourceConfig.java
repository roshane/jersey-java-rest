package com.aeon.api.config;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by roshane on 11/9/2017.
 */
public class ApiResourceConfig extends ResourceConfig {

    public ApiResourceConfig() {
        super();
        packages("com.aeon.api");
    }

}
