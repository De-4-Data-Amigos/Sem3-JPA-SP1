package model;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "person_details")
public class PersonDetails {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String Id;

    @Column(name = "zip",nullable = false)
    private int zipcode;

    @Column(name = "city_name",nullable = false)
    private String cityName;

    @Column(name = "region_name",nullable = false)
    private String regionName;

    @Column(name = "municipality_name",nullable = false)
    private String municipalityName;

    @OneToOne(mappedBy = "personDetails", cascade = CascadeType.ALL)
    private Person person;

    public PersonDetails(int zipcode, String cityName, String regionName, String municipalityName) {
        this.zipcode = zipcode;
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
    }
}
