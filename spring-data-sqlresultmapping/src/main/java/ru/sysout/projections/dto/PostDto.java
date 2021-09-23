package ru.sysout.projections.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    private long id;
    private String title;
    private long userId;
    private String nickname;
}
