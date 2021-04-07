package ru.sysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAnimalService {

    private List<IAnimalRepository> list;

    @Autowired
    public ListAnimalService(List<IAnimalRepository> list) {
        this.list = list;
    }

    public void save() {
        list.forEach(repo -> repo.save());
    }
}
