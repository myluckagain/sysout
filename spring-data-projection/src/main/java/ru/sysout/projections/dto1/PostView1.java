package ru.sysout.projections.dto1;

import org.springframework.beans.factory.annotation.Value;
import ru.sysout.projections.dto.UserDto;

public interface PostView1 {

    long getId();
    String getTitle();
    @Value("#{new ru.sysout.projections.dto.UserDto(target.userId, target.nickname)}")
    UserDto getUser();
}
