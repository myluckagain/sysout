package ru.sysout.projections.dto;

public interface PostView {

    long getId();
    String getTitle();
    UserView getUser();
}
