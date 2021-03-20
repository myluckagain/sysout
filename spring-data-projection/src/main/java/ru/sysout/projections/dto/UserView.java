package ru.sysout.projections.dto;


import org.springframework.beans.factory.annotation.Value;

public interface UserView {

    String getNickname();

    @Value("#{target.email + ' ' + target.password}")
    String getInfo();
}
