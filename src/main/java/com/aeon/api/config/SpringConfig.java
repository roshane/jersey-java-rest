//package com.aeon.api.config;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import javax.sql.DataSource;
//
///**
// * Created by roshane on 3/19/18.
// */
//@Configuration
//public class SpringConfig {
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setPassword("password");
//        dataSource.setUser("dev");
//        dataSource.setServerName("localhost");
//        dataSource.setPort(3306);
//        dataSource.setURL("jdbc:mysql://localhost:3306/develop");
//        return dataSource;
//    }
//
//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//}
