package cz.muni.fi.pa165.rest.mixin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import cz.muni.fi.pa165.dto.ImageDetailDTO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marek Petrovič
 */
@JsonIgnoreProperties({"imageTitle","gallery"})
public abstract class MovieDetailDtoMixin {
    private ImageDetailDTO imageTitle;
    private Set<ImageDetailDTO> gallery = new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate yearMade;

}
