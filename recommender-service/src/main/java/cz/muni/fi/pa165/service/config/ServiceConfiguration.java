package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for service layer.
 * Improvement to handle LocalDate conversions.
 * Based on https://github.com/fi-muni/PA165 example project
 * @author Marek Petrovič
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.service")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");
        mappingFiles.add("dozerMapping.xml");

        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }

    // Argon2 is the best Key Derivation Function since 2015
    @Bean
    public PasswordEncoder encoder() {
        return new Argon2PasswordEncoder();
    }
}
