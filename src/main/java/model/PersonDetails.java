package model;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "person_details")

@NamedQueries({
        @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")

})
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

    public PersonDetails(int zipcode, String cityName, String regionName, String municipalityName) {
        this.zipcode = zipcode;
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
    }
}
