package ru.sysout.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "city",
            orphanRemoval = true)
    private List<District>  districts=new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public void addDistrict(District district){
        this.districts.add(district);
        district.setCity(this);
    }
    public void removeDistrict(District district){
        this.districts.remove(district);
        district.setCity(null);
    }
}
