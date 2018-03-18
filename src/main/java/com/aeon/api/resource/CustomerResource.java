package com.aeon.api.resource;

import com.aeon.api.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by roshane on 11/9/2017.
 */
@Path("hello")
public class CustomerResource {

    private static final Logger logger = LogManager.getLogger("APP");
    private static final String SERVER_PORT = System.getProperty("server.port");

    @Autowired
    private DataSource dataSource;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> apiInfo() throws Exception {
//        throw new Exception("internal server error");
        logger.debug("hit for server [{}]", SERVER_PORT);
//        throw new InternalServerErrorException("error getting resource");
     return Collections.singletonMap("message", "api version V1.0.0");
    }

    @GET
    @Path("/1")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, LocalDateTime> getTime() {
        logger.debug("hit for server [{}]", SERVER_PORT);
        return Collections.singletonMap("dateTime", LocalDateTime.now());
    }

    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findAllCustomers() {
        logger.debug("fetching all customers");
        return getCustomers();
    }

    private List<Customer> getCustomers() {
        String sql = "SELECT * FROM students";
        return namedParameterJdbcTemplate.query(sql, Collections.emptyMap(), (rs, i) -> {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            return customer;
        });
    }
}
