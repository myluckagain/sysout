package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnimalRepository {

    private Logger logger;

     @Autowired(required = false)
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void save() {
        if (logger != null)
            logger.log("logged");
        System.out.println("save method");
    }
}
