package cz.muni.fi.pa165.rest.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.rest.mixin.MovieCreateDtoMixin;
import cz.muni.fi.pa165.rest.mixin.MovieDetailDtoMixin;
import cz.muni.fi.pa165.rest.mixin.MovieListDtoMixin;
import cz.muni.fi.pa165.rest.mixin.UserDTOMixin;
import cz.muni.fi.pa165.sampledata.SampleDataConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

/**
 * Configures a REST application with HATEOAS responses using HAL format. See
 * <ul>
 * <li><a href="http://docs.spring.io/spring-hateoas/docs/current/reference/html/">Spring HATEOAS</a></li>
 * <li><a href="https://apigility.org/documentation/api-primer/halprimer">Hypertext Application Language (HAL)</a></li>
 * <li><a href="https://en.wikipedia.org/wiki/Hypertext_Application_Language">Hypertext Application Language (Wikipedia)</a></li>
 * </ul>
 * Controllers responses use the content-type "application/hal+json", the response is a JSON object
 * with "_links" property for entities, or with "_links" and "_embedded" properties for collections.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */

@EnableHypermediaSupport(type = HypermediaType.HAL)
@EnableWebMvc
@Configuration
@Import({SampleDataConfiguration.class})
@ComponentScan(basePackages = {"cz.muni.fi.pa165.rest.controllers", "cz.muni.fi.pa165.rest.hateoas"})
public class RestSpringMvcConfig implements WebMvcConfigurer {


    @Bean
    @Primary
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper());
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }

    // see  http://stackoverflow.com/questions/25709672/how-to-change-hal-links-format-using-spring-hateoas
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer c) {
        c.defaultContentType(MediaTypes.HAL_JSON);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllowOriginInterceptor());
    }


    @Bean
    public ObjectMapper objectMapper() {
        //configuring mapper for HAL
        ObjectMapper objectMapper = new ObjectMapper();
        //added Mixins to omit some properties in JSONs (now you don't need dummy objects)
        objectMapper.addMixIn(MovieDetailDTO.class, MovieDetailDtoMixin.class);
        objectMapper.addMixIn(MovieListDTO.class, MovieListDtoMixin.class);
        objectMapper.addMixIn(MovieCreateDTO.class, MovieCreateDtoMixin.class);
        objectMapper.addMixIn(UserDTO.class, UserDTOMixin.class);
        objectMapper.addMixIn(UserPasswordlessDTO.class, UserDTOMixin.class);

        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH));
        return objectMapper;
    }

    /**
     * Provides JSR-303 Validator.
     *
     * @return JSR-303 validator
     */
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }


}

