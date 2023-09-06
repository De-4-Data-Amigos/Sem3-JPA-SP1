package model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "person_details")

@NamedQueries({
        // US 6
        @NamedQuery(name = "Person.findAllUsersInACity", query = "SELECT p FROM PersonDetails p WHERE p.cityName = :cityName"),
        // US 7
        @NamedQuery(name = "Person.findAllZipAndCityNames", query = "SELECT p.cityName, p.zipcode FROM PersonDetails p")
})

//
public class PersonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "zip",nullable = false)
    private int zipcode;

    @Column(name = "address", nullable = false)
    private String address;

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


    @OneToOne( cascade = CascadeType.PERSIST)
    private Person person;



    public PersonDetails(int zipcode, String cityName, String regionName, String municipalityName) {

        this.zipcode = zipcode;
        this.address = address;
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

    public void setPerson(Person person) {
        this.person = person;
    }
}



