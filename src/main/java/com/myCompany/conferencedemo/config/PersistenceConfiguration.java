package com.myCompany.conferencedemo.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

public class PersistenceConfiguration {

  @Bean
  public DataSource dataSource(){
    DataSourceBuilder builder = DataSourceBuilder.create();
    builder.url("jdbc:postgresql://localhost:5432/conference");
    return builder.build();
  }

}
