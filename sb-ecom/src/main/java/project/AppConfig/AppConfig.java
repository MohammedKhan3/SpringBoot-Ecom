package project.AppConfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * We can use modelMapper to create DTOs
 * and model mapper is a bean.
 * What is bean ?
 *  Objects that are managed by frameworks are known as bean.
 *  A eban definition includes configuration metsdata that the container needs to know to
 *  create and manage bean.
 *  method modelMapper modelMapper()-> returns ModelMapper
 */
@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
