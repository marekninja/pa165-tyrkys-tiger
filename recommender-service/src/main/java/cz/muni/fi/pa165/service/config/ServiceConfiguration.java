package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.MovieListDTO;
import cz.muni.fi.pa165.entity.Movie;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
//        dozerBeanMapper.addMapping(new DozerCustomMapping());
        return dozerBeanMapper;
    }

//    public class DozerCustomMapping extends BeanMappingBuilder{
//
//        @Override
//        protected void configure() {
//            mapping(Movie.class, MovieDetailDTO.class);
//        }
//    }


}
