package com.aeon.api.resource;

import org.glassfish.jersey.server.ResourceConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by roshane on 11/9/2017.
 */
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        super();
    }

    private static class ResourceSupplier implements Supplier<String>{

        @Override
        public String get() {
            return null;
        }
        static final Map<String,String> map=new HashMap<>();
        public static Optional<String> getForCode(final Integer code) {
            return Optional.ofNullable(code)
                    .map(map::get);
        }
    }
}
