package ru.sysout.projections.dao;

import ru.sysout.projections.dto.PostDto;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostDto> findPostDtos();

}
