package ru.sysout.modelmapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    Long id;

    String title;

    String text;

    LocalDateTime createdDateTime;

    @JsonProperty("user")
    UserDto userDto;

}
