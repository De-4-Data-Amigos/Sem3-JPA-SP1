package model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "person_details")

//@NamedQueries({
//        @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")
//
//})
public class PersonDetails {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int Id;

    @Column(name = "zip",nullable = false)
    private int zipcode;

    @Column(name = "city_name",nullable = false)
    private String cityName;

    @Column(name = "region_name",nullable = false)
    private String regionName;

    @Column(name = "municipality_name",nullable = false)
    private String municipalityName;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "modification_date")
    private LocalDate modificationDate;


    @OneToOne(mappedBy = "personDetails", cascade = CascadeType.ALL)
    private Person person;

    public PersonDetails(int zipcode, String cityName, String regionName, String municipalityName) {
        this.zipcode = zipcode;
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
    }

    @PrePersist
    private void onPrePersist(){
        LocalDate ld = LocalDate.now();
        creationDate = ld;
        modificationDate = ld;
    }

    @PreUpdate
    private void onPreUpdate(){
        modificationDate = LocalDate.now();
    }
}
